<template>
  <div class="d-flex flex-column p-2 justify-content-center">
    <div align="center">
      <h1>History of operations</h1>
    </div>
    <br>
    <div class="d-flex flex-row flex-wrap">
        <div
          class="card border-primary"
          v-for="o in operations" v-bind:key="o.id"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <h4 class="card-title">Date: {{o.date}}</h4>
            <p class="card-text">Clinic: {{o.operationRoom.clinic.name}}</p>
            <p class="card-text">Operation room: {{o.operationRoom.name}}</p>
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
      operations: []
    };
  },
  mounted() {
      var email = localStorage.getItem("Email");

      httpClient
        .get("/operation/patient/" + email)
        .then(response => {
        this.operations = _.cloneDeep(response.data);
      })
      .catch(error => {
        alert(error);
      });
  }
};
</script>
