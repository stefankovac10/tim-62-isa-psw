<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1>Search doctors</h1>
      <br />

      <label class="p-2">First name</label>
      <input type="text" class="p-2" id="firstName" name="firstName" v-model="firstName" />

      <label class="p-2">Last name</label>
      <input type="text" class="p-2" id="lastName" name="lastName" v-model="lastName" />

      <label class="p-2">E-mail</label>
      <input type="text" class="p-2" id="email" name="email" v-model="email" />

      <label class="p-2">City</label>
      <input type="text" class="p-2" id="city" name="city" v-model="city" />

      <label class="p-2">Country</label>
      <input type="text" class="p-2" id="country" name="country" v-model="country" />

      <label class="p-2">Clinic</label>
      <input type="text" class="p-2" id="clinic" name="clinic" v-model="clinic" placeholder="Enter name of the clinic"/>

      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="search">Search</button>

      <br />
    </form>

    <table class="table table-hover" id="doctorsTable">
      <thead>
        <tr>
          <th scope="col">First Name</th>
          <th scope="col">Last Name</th>
          <th scope="col">e-mail</th>
          <th scope="col">City</th>
          <th scope="col">Country</th>
          <th scope="col">Clinic</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="doctor in doctors" :key="doctor.id" class="table-primary">
          <td>{{doctor.firstName}}</td>
          <td>{{doctor.lastName}}</td>
          <td>{{doctor.email}}</td>
          <td>{{doctor.city}}</td>
          <td>{{doctor.country}}</td>
          <td>DUMMY</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";

export default {
  data: function() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      city: "",
      country: "",
      clinic: "",
      doctors: []
    };
  },
  mounted() {
    httpClient
      .get("/users/doc/all")
      .then(response => {
        this.doctors = _.cloneDeep(response.data);
      })
      .catch(error => {
        alert(error);
      });
  },
  methods: {
    search: function() {
      let fn = "", ln = "", e = "", cty = "", cntry = "", clnc = "";

      if(this.firstName === ""){ fn = "_"; } else { fn = this.firstName; }
      if(this.lastName === ""){ ln = "_"; } else { ln = this.lastName; }
      if(this.email === ""){ e = "_"; } else { e = this.email; }
      if(this.city === ""){ cty = "_"; } else { cty = this.city; }
      if(this.country === ""){ cntry = "_"; } else { cntry = this.country; }
      if(this.clinic === ""){ clnc = "_"; } else { clnc = this.clinic; }

      httpClient
        .get("/users/doc/search/" + fn + "/" + ln + "/" + e + "/" + cty + "/" + cntry + "/" + clnc)
        .then(response => {
          this.response = response;
          this.doctors = response.data;
        })
        .catch(error => {
          this.error = error;
        });
    }
  }
};
</script>
