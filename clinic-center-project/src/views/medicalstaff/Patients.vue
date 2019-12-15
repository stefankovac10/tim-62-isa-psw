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

      <div id="results" class="d-flex flex-row flex-wrap">
        <div
          v-for="patient in filtered"
          v-bind:key="patient.id"
          class="card border-primary mb-3"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        >
          <div class="card-body">
            <h4 class="card-title">{{patient.firstName}} {{patient.lastName}}</h4>
            <p class="card-text">Jmbg: {{patient.jmbg}}</p>
          </div>
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
      filtered: [],
      response: undefined
    };
  },
  mounted() {
    httpClient
      .get("/patient/all")
      .then(response => {
        this.patients = _.cloneDeep(response.data);
        this.filtered = _.cloneDeep(response.data);
      })
      .catch(error => {
        alert(error);
      });
  },
  methods: {
    search: function() {
      // httpClient
      //   .get(
      //     "/patient/search/" +
      //       this.firstName +
      //       "/" +
      //       this.lastName +
      //       "/" +
      //       this.jmbg
      //   )
      //   .then(response => {
      //     this.patients = _.cloneDeep(response.data);
      //     for (let p of this.patients) {
      //       this.filtered.push(p);
      //     }
      //   })
      //   .catch(error => {
      //     alert(error);
      //   });
      let del = [];
      for (let p of this.patients) {
        if (!p.firstName.toLowerCase().includes(this.firstName.toLowerCase())) {
          del.push(this.patients.indexOf(p));
        } else if (
          !p.lastName.toLowerCase().includes(this.lastName.toLowerCase())
        ) {
          del.push(this.patients.indexOf(p));
        } else if (!p.jmbg.includes(this.jmbg)) {
          del.push(this.patients.indexOf(p));
        }

        for (let i of del) {
          this.filtered.splice(i, 1);
        }
      }
    },
    clear: function() {
      this.firstName = "";
      this.lastName = "";
      this.jmbg = "";
      httpClient.get("/patient/all").then(response => {
        this.patients = _.cloneDeep(response.data);
        this.filtered = _.cloneDeep(response.data);
      });
    }
  }
};
</script>
