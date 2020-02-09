<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column p-2 justify-content-center">
      <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
        <div
          v-for="ms in medicalStaff"
          v-bind:key="ms.id"
          class="card border-primary mb-3"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <h4 class="card-title">{{ms.type}}: {{ms.firstName}} {{ms.lastName}}</h4>
            <p class="card-text">Address: {{ms.address}}</p>
            <p class="card-text">Jmbg: {{ms.jmbg}}</p>
            <p class="card-text">Telephone: {{ms.telephone}}</p>
            <p class="card-text">Email: {{ms.email}}</p>
            <!-- <button type="button" class="btn btn-primary" v-on:click="edit">Edit</button> -->
            <button type="button" class="btn btn-danger" v-on:click="remove(ms)">Delete</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";

export default {
  name: "listDiagnosis",
  data: function() {
    return {
      firstName: undefined,
      lastName: undefined,
      jmbg: undefined,
      telephone: undefined,
      country: undefined,
      city: undefined,
      address: undefined,
      email: undefined,
      clinic: undefined,
      type: undefined,
      medicalStaff: undefined
    };
  },
  watch: {
    type: function() {
      if (this.type === "Doctors") {
        this.show = this.docs;
      } else if (this.type === "Nurses") {
        this.show = this.nurses;
      }
    }
  },
  mounted() {
    httpClient
      .get("/users/admin/mail/" + localStorage.getItem("Email"))
      .then(response => {
        this.clinic = response.data.clinic;
      })
      .catch(() => {
        this.$vToastify.error({
          body: "Error retrieving admin",
          title: "Error",
          type: "error",
          canTimeout: true,
          append: false,
          duration: 2000
        });
      })
      .then(() => {
        httpClient
          .get("/clinics/" + this.clinic.id)
          .then(response => {
            this.medicalStaff = _.cloneDeep(response.data.medicalStaff);
          })
          .catch(() => {
            this.$vToastify.error({
              body: "Error retrieving clinic",
              title: "Error",
              type: "error",
              canTimeout: true,
              append: false,
              duration: 2000
            });
          });
      });
  },
  methods: {
    remove: function(ms) {
      let tajp = ms.type === "doctor" ? "/users/doc/" : "/nurse/";

      httpClient
        .delete(tajp + ms.id)
        .then(() => {
          this.$vToastify.info({
            body: "Successfully deleted " + ms.type,
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Doctor has scheduladed examinations",
            title: "Error deleting doctor",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .then(() => {
          location.reload();
        });
    }
  }
};
</script>