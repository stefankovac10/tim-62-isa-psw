<template>
    <div class="d-flex flex-column p-2 justify-content-center">  
        <h1 align="center">Operation requests </h1>
        <div id="results" class="justify-content-center">
        <table class="table table-hover">
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
              <th scope="row">{{request.date}}</th>
              <td>{{request.duration/60000}} min</td>
              <td>{{request.patientName}}</td>
              <td>{{request.doctorName}}</td>
              <td> <button class="btn btn-success" >Schedule</button></td>
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
      </div>
    </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";
export default {
  data: function() {
    return {
      pages: [],
      requests: undefined
    };
  },
  mounted() {
    httpClient
        .get("/operationRequest/"+localStorage.getItem('Email')+'/0')
        .then(response => {
          this.requests = _.cloneDeep(response.data);
          this.pages = [];
          for (let i = 1; i <= this.requests[0].pages; i++) {
            this.pages[i - 1] = i;
          }
        })
        .catch(error => {
          this.error = error;
          alert(error);
        });
  },
  methods: {
    nextPage: function(page) {
        httpClient
          .get("/operationRequest/"+localStorage.getItem('Email')+'/' + page)
          .then(response => {
            this.requests = _.cloneDeep(response.data);
            this.pages = [];
            if (this.requests[0].pages != undefined) {
              for (let i = 1; i <= this.requests[0].pages; i++) {
                this.pages[i - 1] = i;
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