<template>
  <div class="d-flex flex-column p-2 justify-content-center">
    <div align="center">
      <h1>History of examinations</h1>
    </div>
    <br>
    <div class="d-flex flex-row flex-wrap">
        <div
          class="card border-primary"
          v-for="e in examinations" v-bind:key="e.id"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <h4 class="card-title">Date: {{e.date}}</h4>
            <p class="card-text">Doctor: {{e.doctor.firstName + " " + e.doctor.lastName}}</p>
            <p class="card-text">Descripion: {{e.report}}</p>
          </div>
        </div>
      </div>
  </div>
  
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";

export default {
  data: function() {
    return {
      examinations: []
    };
  },
  mounted() {
      var email = localStorage.getItem("Email");

      httpClient
        .get("/examination/patient/" + email)
        .then(response => {
        this.examinations = _.cloneDeep(response.data);
      })
      .catch(error => {
        alert(error);
      });
  }
};
</script>
