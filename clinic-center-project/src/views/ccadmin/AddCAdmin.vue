<template>
  <div id="form" class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Add Clinical Administrator</h1>
      <br />

      <label class="p-2">Name</label>
      <input type="text" class="p-2" id="name" name="name" v-model="name" placeholder="Enter first name"/>

      <label class="p-2">Surname</label>
      <input
        type="text"
        class="p-2"
        id="surname"
        name="surname"
        v-model="surname"
        placeholder="Enter last name"
      />

      <label class="p-2">JMBG</label>
      <input type="text" class="p-2" id="jmbg" name="jmbg" v-model="jmbg" placeholder="Enter jmbg" />

      <label class="p-2">Telephone</label>
      <input
        type="text"
        class="p-2"
        id="telephone"
        name="telephone"
        v-model="telephone"
        placeholder="Enter telephone"
      />

      <label class="p-2">Country</label>
      <input
        type="text"
        class="p-2"
        id="country"
        name="country"
        v-model="country"
        placeholder="Enter country"
      />

      <label class="p-2">City</label>
      <input type="text" class="p-2" id="city" name="city" v-model="city" placeholder="Enter city" />

      <label class="p-2">Address</label>
      <input
        type="text"
        class="p-2"
        id="address"
        name="address"
        v-model="address"
        placeholder="Enter address"
      />

      <label for="staticEmail" class="p-2">E-mail</label>
      <input
        type="email"
        class="p-2"
        id="email"
        name="email"
        v-model="email"
        aria-describedby="emailHelp"
        placeholder="Enter email"
      />
      <br />

      <label class="p-2" for="password">Password</label>
      <input
        type="password"
        class="p-2"
        id="password"
        name="password"
        v-model="password"
        placeholder="Password"
      />
      
      <br />
      <div class="form-group">
        <label for="exampleSelect1">Clinic</label>
        <select class="form-control" id="exampleSelect1" v-model="clinic" >
            <option v-for="clinic in clinics" :key="clinic">{{clinic.name}}</option>
        </select>
      </div>

      <br />
      <button class="btn btn-primary p-2" v-on:click="add">Add</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
export default {
  name: "addCAdmin",
  data: function() {
    return {
      name: undefined,
      surname: undefined,
      jmbg: undefined,
      telephone: undefined,
      country: undefined,
      city: undefined,
      address: undefined,
      email: undefined,
      password: undefined,
      clinic: undefined,
      clinics: {}
    };
  },
  mounted(){
    httpClient
        .get("/clinics/all")
        .then(response => {
          this.clinics = response.data;      
        })
        .catch(error => {
          this.error = error;
        });

  },
  methods:{
      add: function(){
        httpClient
        .post("/cadmin", {
          firstName: this.name,
          lastName: this.surname,
          jmbg: this.jmbg,
          password: this.password,
          email: this.email,
          address: this.address,
          city: this.city,
          country: this.country,
          telephone: this.telephone,
          clinic: this.clinic
        })
        .then(response => {
          this.response = response;
          alert("Administrator successufully added"); 
        })
        .catch(error => {
          this.error = error;
        });
      }
  }
};
</script>
