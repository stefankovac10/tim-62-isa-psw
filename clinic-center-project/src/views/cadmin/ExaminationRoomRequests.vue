<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column">
      <h1>Requests</h1>
      <div id="results" class="justify-content-center">
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">Patient</th>
              <th scope="col">Doctor</th>
              <th scope="col">Date</th>
              <th scope="col">Duration</th>
            </tr>
          </thead>
          <tbody>
            <tr
              class="table-primary"
              v-for="request in requests"
              v-bind:key="request.id"
              v-on:click="search(request)"
            >
              <td>{{request.patient.firstName}} {{request.patient.lastName}}</td>
              <td>{{request.doctor.firstName}} {{request.doctor.lastName}}</td>
              <td>{{request.dateMoment}}</td>
              <td>{{request.durationMoment}} minutes</td>
            </tr>
          </tbody>
        </table>
        <div>
          <ul class="pagination">
            <!-- <li class="page-item disabled">
              <a class="page-link" href="#">&laquo;</a>
            </li>-->
            <li class="page-item active" v-for="i in pages" v-bind:key="i">
              <a class="page-link" v-on:click="nextPage(i - 1)">{{i}}</a>
            </li>
            <!-- <li class="page-item">
              <a class="page-link">&raquo;</a>
            </li>-->
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import moment from "moment";
import EventBus from "@/services/EventBus.js";

export default {
  data: function() {
    return {
      requests: undefined,
      pages: 0
    };
  },
  mounted() {
    httpClient
      .get("/cadmin/scheduledExaminations/0")
      .then(response => {
        this.requests = response.data;
        this.pages = response.data[0].pages;
        for (const request of this.requests) {
          request.dateMoment = moment(request.date).format(
            "dddd, MMMM Do YYYY, h:mm:ss"
          );
          request.durationMoment = moment(request.duration).minute();
        }
      })
      .catch(() => {
        this.$vToastify.error({
          body: "Error getting requests",
          title: "Error",
          type: "error",
          canTimeout: true,
          append: false,
          duration: 2000
        });
      });
  },
  methods: {
    search: function(request) {
      EventBus.$emit("search", request);
      this.$router.push("/cadmin/rooms");
    }
  }
};
</script>