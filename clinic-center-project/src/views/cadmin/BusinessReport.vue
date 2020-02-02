<template>
  <div class="d-flex p-2 justify-content-center">
    <div v-if="!loading" class="d-flex flex-column">
      <h1>Business report</h1>
      <h3>Grade: {{clinic.grade}}</h3>
      <h3>The doctor's grades:</h3>
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
            <h5 class="card-text">Grade: {{ms.grade}}</h5>
          </div>
        </div>
      </div>
      <h3>Examination charts:</h3>
      <div class="d-flex flex-row p-3 justify-content-center" style="background: white">
        <pure-vue-chart
          class="m-2"
          :points="this.weekly"
          :show-y-axis="false"
          :show-x-axis="true"
          :width="400"
          :height="200"
          :show-values="true"
        />
        <pure-vue-chart
          class="m-2"
          :points="this.monthly"
          :show-y-axis="false"
          :show-x-axis="true"
          :width="500"
          :height="200"
          :show-values="true"
          :use-month-labels="true"
        />
      </div>

      <h3>Income: {{clinic.income}}</h3>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import PureVueChart from "pure-vue-chart";
import moment from "moment";

export default {
  data: function() {
    return {
      clinic: undefined,
      loading: true,
      weekly: [
        { label: "Mon", value: 0 },
        { label: "Tue", value: 0 },
        { label: "Wed", value: 0 },
        { label: "Thu", value: 0 },
        { label: "Fri", value: 0 },
        { label: "Sat", value: 0 },
        { label: "Sun", value: 0 }
      ],
      monthly: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    };
  },
  components: {
    PureVueChart
  },
  mounted() {
    this.loading = true;
    httpClient
      .get("/clinics/businessReport/1")
      .then(response => {
        this.clinic = response.data;
        this.loading = false;
        for (let ex of response.data.examinations) {
          let d = moment(ex.date);
          this.weekly[d.get("date") - 1].value =
            this.weekly[d.get("date") - 1].value + 1;
          this.monthly[d.get("month")] = this.monthly[d.get("month")] + 1;
        }
      })
      .catch(error => {
        if (error.response != undefined && error.response.status == 302) {
          this.response = error.response.data;
        }
      });
  }
};
</script>
