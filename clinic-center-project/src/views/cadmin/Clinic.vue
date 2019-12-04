<template>
  <div class="d-flex p-2 justify-content-center">
    <div v-if="!loading" class="d-flex flex-column p-2">
      <h1>Clinic</h1>

      <label>Name: {{clinic.name}}</label>
      <label>Description: {{clinic.description}}</label>
      <label>Address: {{clinic.address}}</label>
      <label>Available examinations: TBA: LIST</label>
      <label>Staff:</label>
      <div
        v-for="ms in clinic.medicalStaff"
        v-bind:key="ms.id"
        class="card border-primary mb-3 d-flex flex-row flex-wrap"
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
      <label>Rooms:</label>
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
      <label>Pricelist: TBA: list</label>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      clinic: undefined,
      loading: true
    };
  },
  mounted() {
    httpClient;
    this.loading = true;
    httpClient
      .get("/clinics/1")
      .then(response => {
        this.clinic = response.data;
        this.loading = false;
      })
      .catch(error => {
        if (error.response.status == 302) {
          this.response = error.response.data;
        }
      });
  }
};
</script>
