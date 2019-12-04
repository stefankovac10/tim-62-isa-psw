<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column">
      <h1>Vacation requests</h1>
      <div
        v-for="req in requests"
        v-bind:key="req.id"
        class="card border-primary mb-3 d-flex flex-row flex-wrap"
        style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
      >
        <div class="card-body">
          <h4 class="card-title">{{req.medicalStaff.firstName}} {{req.medicalStaff.lastName}}</h4>
          <p class="card-text">From: {{req.startDate}}</p>
          <p class="card-text">To: {{req.endDate}}</p>

          <button type="button" class="btn btn-primary" v-on:click="accept(id)">Accept</button>
          <button type="button" class="btn btn-danger" v-on:click="refuse">Refuse</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
export default {
  data: function() {
    return {
      requests: undefined
    };
  },
  mounted() {
    httpClient
      .get("/vacs")
      .then(function(response) {
        this.requests = response.data;
      })
      .catch(function(error) {
        if (error.response.status == 302) {
          this.requests = error.response.data;
        }
      });
  }
};
</script>