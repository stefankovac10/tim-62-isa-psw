<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-column">
      <h1 id="requestsMF">Requests</h1>
      <div id="results" class="justify-content-center">
        <label id="noRequests" v-if="!requests" for="empty">Currently there are no new requests</label>
        <table id="requestsTable" class="table table-hover" v-if="requests">
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
              v-on:click="search(request.id)"
              v-bind:id="request.id + 'row'"
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
      <SearchRoomByRequest
        v-bind:type="examination"
        v-if="request"
        v-bind:id="request"
        v-on:reserved="remove"
        v-bind:key="componentKey"
      ></SearchRoomByRequest>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import moment from "moment";
import SearchRoomByRequest from "@/views/cadmin/SearchRoomByRequest.vue";

export default {
  data: function() {
    return {
      requests: undefined,
      request: undefined,
      pages: 0,
      examination: "examination",
      clinic: undefined,
      componentKey: 0
    };
  },
  components: {
    SearchRoomByRequest
  },
  mounted() {
    httpClient
      .get("/users/admin/mail/" + localStorage.getItem("Email"))
      .then(response => {
        this.clinic = response.data.clinic;
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
      })
      .then(() => {
        httpClient
          .get("/cadmin/scheduledExaminations/" + this.clinic.id + "/0")
          .then(response => {
            if (response.data.length === 0) {
              return;
            }
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
      });
  },
  methods: {
    search: function(id) {
      this.request = id;
      this.$children[0].id = id;
      this.componentKey += 1;
    },
    remove: function() {
      this.request = undefined;
      httpClient
        .get("/cadmin/scheduledExaminations/" + this.clinic.id + "/0")
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
            body: "Error removing requests",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        });
    },
    nextPage: function(i) {
      httpClient
        .get("/cadmin/scheduledExaminations/" + i)
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
    }
  }
};
</script>