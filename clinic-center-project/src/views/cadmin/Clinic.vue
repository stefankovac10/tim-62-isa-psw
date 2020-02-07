<template>
  <div class="d-flex p-2 justify-content-center">
    <div v-if="!loading" class="d-flex flex-column p-2">
      <h1>Clinic</h1>
      <button
        id="btn"
        data-toggle="modal"
        data-target="#editModal"
        data-whatever="@mdo"
        v-on:click.prevent="edit()"
      >Edit</button>
      <label>Name: {{clinic.name}}</label>
      <label>Description: {{clinic.description}}</label>
      <label>Address: {{clinic.address}}</label>
      <yandex-map class="map" id="map" v-on:created="mapCreated"></yandex-map>

      <label>Available examinations:</label>
      <label v-if="!examinations">There are no quick examinations at the moment</label>
      <table class="table table-hover" v-if="examinations">
        <thead>
          <tr>
            <th scope="col">Doctor</th>
            <th scope="col">Date</th>
            <th scope="col">Duration</th>
            <th scope="col">Price</th>
          </tr>
        </thead>
        <tbody>
          <tr class="table-primary" v-for="examination in examinations" v-bind:key="examination.id">
            <td>{{examination.doctor.firstName}} {{examination.doctor.lastName}}</td>
            <td>{{examination.dateMoment}}</td>
            <td>{{examination.durationMoment}} minutes</td>
            <td>{{examination.price}}</td>
          </tr>
        </tbody>
      </table>

      <label>Staff:</label>
      <div class="d-flex flex-row flex-wrap">
        <div
          v-for="ms in clinic.medicalStaff"
          v-bind:key="ms.id"
          class="card border-primary mb-3"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <!-- <h4 class="card-title">{{req.medicalStaff.firstName}} {{req.medicalStaff.lastName}}</h4> -->
            <h4 class="card-title">{{ms.fistName}} {{ms.lastName}}</h4>
            <p class="card-text">Address: {{ms.address}}, {{ms.city}}, {{ms.country}}</p>
            <p class="card-text">E-mail: {{ms.email}}</p>
            <p class="card=text">Telephone: {{ms.telephone}}</p>
          </div>
        </div>
      </div>
      <label>Rooms:</label>
      <div class="d-flex flex-row flex-wrap">
        <div
          v-for="room in clinic.rooms"
          v-bind:key="room.id"
          class="card border-primary mb-3 d-flex flex-row flex-wrap"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <!-- <h4 class="card-title">{{req.medicalStaff.firstName}} {{req.medicalStaff.lastName}}</h4> -->
            <h4 class="card-title">Name: {{room.name}}</h4>
            <p class="card-text">Number: {{room.number}}</p>
          </div>
        </div>
      </div>
      <label>Pricelist: TBA: list</label>
      <div id="editModal" class="modal">
        <div class="modal-dialog justify-content-center" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Edit clinic's info</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form id="login" accept-charset="UTF-8" class="d-flex flex-column">
              <div class="modal-body d-flex flex-column">
                <label>Name:</label>
                <input type="text" class="p-2" id="name" name="name" v-model="name" />
                <label>Description:</label>
                <input
                  type="text"
                  class="p-2"
                  id="description"
                  name="description"
                  v-model="description"
                />
                <label>Address:</label>
                <input type="text" class="p-2" id="address" name="address" v-model="address" />
              </div>
              <div class="modal-footer">
                <button
                  type="button"
                  class="btn btn-primary"
                  data-dismiss="modal"
                  v-on:click.prevent="update"
                >Save changes</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import moment from "moment";

export default {
  data: function() {
    return {
      clinic: undefined,
      loading: true,
      map: null,
      map_data: [],
      name: undefined,
      description: undefined,
      address: undefined,
      admin: undefined,
      examinations: undefined
    };
  },
  mounted() {
    this.loading = true;
    httpClient
      .get("/users/admin/mail/" + localStorage.getItem("Email"))
      .then(response => {
        this.admin = response.data;
      })
      .catch(() => {
        this.$vToastify.error({
          body: "Could not get admin",
          title: "Error",
          type: "error",
          canTimeout: true,
          append: false,
          successDuration: 2000
        });
      })
      .then(() => {
        httpClient
          .get("/clinics/" + this.admin.clinic.id)
          .then(response => {
            this.clinic = response.data;
            this.loading = false;
            if (this.clinic.examinations.length != 0) {
              this.examinations = this.clinic.examinations;
              for (const examination of this.examinations) {
                examination.dateMoment = moment(examination.date).format(
                  "dddd, MMMM Do YYYY, h:mm:ss"
                );
                examination.durationMoment = moment(
                  examination.duration
                ).minute();
              }
            }
          })
          .catch(error => {
            if (error.response != undefined && error.response.status == 302) {
              this.response = error.response.data;
            }
          });
      });
  },
  methods: {
    edit: function() {
      this.name = this.clinic.name;
      this.description = this.clinic.description;
      this.address = this.clinic.address;
    },
    update: function() {
      httpClient
        .put("/clinics", {
          id: this.clinic.id,
          name: this.name,
          description: this.description,
          address: this.address
        })
        .then(response => {
          this.name = response.data.name;
          this.description = response.data.description;
          this.address = response.data.address;
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Error editing clinic",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            successDuration: 2000
          });
        })
        .then(() => {
          location.reload();
        });
    }
  }
};
</script>

<style scoped>
#map {
  height: 200px;
  width: 280px;
}
#btn {
  width: 80px;
}
</style>