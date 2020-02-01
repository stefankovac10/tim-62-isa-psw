<template>
  <div class="d-flex  p-2 justify-content-center">
    <div class="d-flex flex-column p-2">
      <h1>Patient's info:</h1>
      <p>Name: {{ patient.firstName }} {{ patient.lastName}}</p>
      <p>JMBG: {{ patient.jmbg }}</p>
      <p>Telephone: {{ patient.telephone }}</p>
      <p>Address: {{ patient.address }}, {{ patient.city }}, {{ patient.country }}</p>
      <p>Email: {{ patient.email }}</p>
      <button type="button" class="btn btn-danger" style="width: 150px" v-on:click="goMedicalRecord">Medical record</button>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
export default {
  data: function() {
    return {
      id: undefined,
      patient: undefined
    };
  },
  mounted(){
    this.id = this.$route.params.id;
    httpClient
      .get("/patient/" + this.id)
      .then(response => {
        this.patient = response.data;
      })
      .catch(error => {
        alert(error);
      });

  },
  methods:{
    goMedicalRecord: function(){
      this.$router.push("/doc/editMedicalRecord/"+this.id)
    }
  }
};
</script>
