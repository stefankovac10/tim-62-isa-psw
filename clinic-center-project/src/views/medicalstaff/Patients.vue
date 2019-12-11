<template>
  <div class="d-flex flex-column p-2 justify-content-center">
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
      <button class="btn btn-primary p-2" v-on:click.prevent="search">Search</button>
    </form>
    <br />
    <hr />

    <input type="text" class="p-2 col-sm-4" id="filter" name="filter" v-model="filter" />
    <br />
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
      filter: undefined,
      filtered: [],
      response: undefined
    };
  },
  watch: {
    filter() {
      for (let p of this.patients) {
        if (
          !p.firstName.toLowerCase().includes(this.filter.toLowerCase()) &&
          !p.lastName.toLowerCase().includes(this.filter.toLowerCase()) &&
          !p.jmbg.toLowerCase().includes(this.filter.toLowerCase())
        ) {
          this.filtered.splice(this.filtered.indexOf(p), 1);
        }
        if (
          !this.filtered.includes(p) &&
          (p.firstName.toLowerCase().includes(this.filter.toLowerCase()) ||
            p.lastName.toLowerCase().includes(this.filter.toLowerCase()) ||
            p.jmbg.toLowerCase().includes(this.filter.toLowerCase()))
        ) {
          this.filtered.push(p);
        }
      }
    }
  },
  methods: {
    search: function() {
      httpClient
        .get(
          "/patient/search/" +
            this.firstName +
            "/" +
            this.lastName +
            "/" +
            this.jmbg
        )
        .then(response => {
          this.patients = _.cloneDeep(response.data);
          for (let p of this.patients) {
            this.filtered.push(p);
          }
        })
        .catch(error => {
          alert(error);
        });
    }
  }
};
</script>
