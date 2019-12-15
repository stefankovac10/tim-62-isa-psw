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
            <h4 class="card-title">Name: {{ms.firstName}} {{ms.lastName}}{{ms.id}}</h4>
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
      .get("/clinics/1")
      .then(response => {
        this.medicalStaff = _.cloneDeep(response.data.medicalStaff);
      })
      .catch(error => {
        alert(error);
      });
  },
  methods: {
    remove: function(ms) {
      httpClient
        .delete("/users/15") // + ms.id)
        .then(response => {
          response;
        })
        .catch(error => {
          alert(error);
        });
      this.medicalStaff.splice(this.medicalStaff.indexOf(ms), 1);
      this.$vToastify.info({
        body: "Doctor "+ this.firstName + " " + this.lastName + " has been deleted." ,
        title: "Success",
        type: "success",
        canTimeout: true,
        append: false, duration: 2000
      });
    }
  }
};
</script>