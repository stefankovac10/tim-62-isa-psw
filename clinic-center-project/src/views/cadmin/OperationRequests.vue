<template>
    <div class="d-flex flex-column p-2 justify-content-center">  
        <h1 align="center">Operation requests </h1>
        <div id="results" class="justify-content-center">
        <label v-if="!requests" for="empty">Currently there are no new requests</label>
        <table class="table table-hover" v-if="requests">
          <thead>
            <tr>
              <th scope="col">Date</th>
              <th scope="col">Duration</th>
              <th scope="col">Patient</th>
              <th scope="col">Doctor</th>
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr class="table-primary" v-for="request in requests" v-bind:key="request.id">
              <th scope="row">{{request.dateMoment}}</th>
              <td>{{request.durationMoment}} min</td>
              <td>{{request.patientName}}</td>
              <td>{{request.doctorName}}</td>
              <td> <button class="btn btn-success"  v-on:click="search(request.id)">Book room</button></td>
            </tr>
          </tbody>
        </table>
        <div  class="d-flex p-2 justify-content-center">
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
        <SearchRoomByRequest
          v-bind:type="operation"
          v-if="request"
          v-bind:id="request"
          v-on:reserved="refresh"
        ></SearchRoomByRequest>
      </div>
    </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";
import SearchRoomByRequest from "@/views/cadmin/SearchRoomByRequest.vue";
import moment from "moment";
export default {
  data: function() {
    return {
      pages: [],
      requests: undefined,
      request: undefined,
      operation: "operation"
    };
  },
  components: {
    SearchRoomByRequest
  },
  mounted() {
    httpClient
        .get("/operationRequest/"+localStorage.getItem('Email')+'/0')
        .then(response => {
          this.requests = _.cloneDeep(response.data);
          this.pages = this.requests[0].pages;
          for (const request of this.requests) {
            request.dateMoment = moment(request.date).format(
              "dddd, MMMM Do YYYY"
            );
            request.durationMoment = moment(request.duration).minute();
          }
        })
        .catch(error => {
          this.error = error;
          alert(error);
        });
  },
  methods: {
    refresh: function(){
        httpClient
        .get("/operationRequest/"+localStorage.getItem('Email')+'/0')
        .then(response => {
          this.requests = _.cloneDeep(response.data);
          this.pages = this.requests[0].pages;
          for (const request of this.requests) {
            request.dateMoment = moment(request.date).format(
              "dddd, MMMM Do YYYY, h:mm:ss"
            );
            request.durationMoment = moment(request.duration).minute();
          }
        })
        .catch(error => {
          this.error = error;
          alert(error);
        });
    },
    search: function(id) {
      this.request = id;
    },
    nextPage: function(page) {
        httpClient
          .get("/operationRequest/"+localStorage.getItem('Email')+'/' + page)
          .then(response => {
            this.requests = _.cloneDeep(response.data);
            this.pages = [];
            if (this.requests[0].pages != undefined) {
              this.pages = this.requests[0].pages;
              for (const request of this.requests) {
                request.dateMoment = moment(request.date).format(
                  "dddd, MMMM Do YYYY, h:mm:ss"
                );
                request.durationMoment = moment(request.duration).minute();
              }
            }
          })
          .catch(error => {
            alert(error);
          });
    },
         
  }
};
</script>