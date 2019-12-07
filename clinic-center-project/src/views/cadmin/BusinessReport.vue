<template>
  <div class="d-flex p-2 justify-content-center">
    <div v-if="!loading" class="d-flex flex-column">
      <h1>Business report</h1>
      <label>Grade: {{clinic.grade}}</label>
      <label>The doctor's grades:</label>
      <div class="d-flex flex-row flex-wrap">
        <div
          v-for="ms in clinic.medicalStaff"
          v-bind:key="ms.id"
          class="card border-primary mb-3"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <!-- <h4 class="card-title">{{req.medicalStaff.firstName}} {{req.medicalStaff.lastName}}</h4> -->
            <h4 class="card-title">Name: {{ms.firstName}} {{ms.lastName}}</h4>
            <h3 class="card-text">Grade: {{ms.grade}}</h3>
          </div>
        </div>
      </div>
      <div>Grafik pregleda: TBA</div>
      <label>Income: {{clinic.income}}</label>
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
    this.loading = true;
    httpClient
      .get("/clinics/businessReport/1")
      .then(response => {
        this.clinic = response.data;
        this.loading = false;
      })
      .catch(error => {
        if (error.response != undefined && error.response.status == 302) {
          this.response = error.response.data;
        }
      });
  }
};
</script>
