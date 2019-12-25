<template>
  <div class="d-flex p-2 justify-content-center">
    <div class="d-flex flex-row">
      <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
        <h1 class="p-2">Search patients</h1>
        <br />

        <label class="p-2">First name</label>
        <input type="text" class="p-2" id="firstName" name="firstName" v-model="firstName" />

        <label class="p-2">Last name</label>
        <input type="text" class="p-2" id="lastName" name="lastName" v-model="lastName" />

        <label class="p-2">JMBG</label>
        <input type="text" class="p-2" id="jmbg" name="jmbg" v-model="jmbg" />
        <br />
        <div class="d-flex flex-row">
          <button class="btn btn-primary m-3" v-on:click.prevent="search">Search</button>
          <button class="btn btn-primary m-3" v-on:click.prevent="clear">Clear</button>
        </div>
      </form>
      <br />
      <hr />

      <div id="results" class="justify-content-center">
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">First name</th>
              <th scope="col">Last name</th>
              <th scope="col">JMBG</th>
              <th scope="col">E-mail</th>
              <th scope="col">Telephone</th>
            </tr>
          </thead>
          <tbody>
            <tr class="table-primary" v-for="patient in patients" v-bind:key="patient.id">
              <th scope="row">{{patient.firstName}}</th>
              <td>{{patient.lastName}}</td>
              <td>{{patient.jmbg}}</td>
              <td>{{patient.email}}</td>
              <td>{{patient.telephone}}</td>
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
import _ from "lodash";

export default {
  data: function() {
    return {
      firstName: undefined,
      lastName: undefined,
      jmbg: undefined,
      patients: undefined,
      next: "all",
      response: undefined,
      pages: []
    };
  },
  mounted() {
    httpClient
      .get("/patient/all/0")
      .then(response => {
        this.patients = _.cloneDeep(response.data);
        this.filtered = _.cloneDeep(response.data);
        this.pages = [];
        for (let i = 1; i <= this.patients[0].pages; i++) {
          this.pages[i - 1] = i;
        }
        this.next = "all";
      })
      .catch(error => {
        alert(error);
      });
  },
  methods: {
    search: function() {
      let fn = "";
      let ln = "";
      let j = "";
      if (this.firstName === "" || this.firstName === undefined) {
        fn = "_";
      } else fn = this.firstName;
      if (this.lastName === "" || this.lastName === undefined) {
        ln = "_";
      } else ln = this.lastName;
      if (this.jmbg === "" || this.jmbg === undefined) {
        j = "_";
      } else j = this.jmbg;
      httpClient
        .get("/patient/search/" + fn + "/" + ln + "/" + j + "/" + 0)
        .then(response => {
          this.patients = _.cloneDeep(response.data);
          this.pages = [];
          if (this.patients[0].pages != undefined) {
            for (let i = 1; i <= this.patients[0].pages; i++) {
              this.pages[i - 1] = i;
            }
          }
          this.next = "search";
        })
        .catch(error => {
          error;
          this.$vToastify.error({
            body: "There are no patients with given arguments",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false
          });
        });
    },
    clear: function() {
      this.firstName = "";
      this.lastName = "";
      this.jmbg = "";
      httpClient.get("/patient/all/0").then(response => {
        this.patients = _.cloneDeep(response.data);
        this.pages = [];
        if (this.patients[0].pages != undefined) {
          for (let i = 1; i <= this.patients[0].pages; i++) {
            this.pages[i - 1] = i;
          }
        }
        this.next = "all";
      });
    },
    nextPage: function(page) {
      if (this.next === "all") {
        httpClient
          .get("/patient/all/" + page)
          .then(response => {
            this.patients = _.cloneDeep(response.data);
            this.pages = [];
            if (this.patients[0].pages != undefined) {
              for (let i = 1; i <= this.patients[0].pages; i++) {
                this.pages[i - 1] = i;
              }
            }
            this.next = "all";
          })
          .catch(error => {
            alert(error);
          });
      } else if (this.next === "search") {
        httpClient
          .get(
            "/patient/search/" +
              this.firstName +
              "/" +
              this.lastName +
              "/" +
              this.jmbg +
              "/" +
              page
          )
          .then(response => {
            this.patients = _.cloneDeep(response.data);
            this.pages = [];
            if (this.patients[0].pages != undefined) {
              for (let i = 1; i <= this.patients[0].pages; i++) {
                this.pages[i - 1] = i;
              }
            }
            this.next = "search";
          })
          .catch(error => {
            error;
            this.$vToastify.error({
              body: "There are no patients with given arguments",
              title: "Error",
              type: "error",
              canTimeout: true,
              append: false
            });
          });
      }
    }
  }
};
</script>
