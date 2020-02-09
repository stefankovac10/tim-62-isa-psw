<template>
  <div>
    <div>
      <h3 align="center">Select which doctor you want to examine you</h3>
    </div>
    <div class="d-flex p-2 justify-content-center">
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
          <tr v-for="doctor in doctors" :key="doctor.id" class="table-primary" v-on:click="redirectFurther(doctor.id)">
            <td>{{doctor.firstName}}</td>
            <td>{{doctor.lastName}}</td>
            <td>{{doctor.email}}</td>
            <td>{{doctor.city}}</td>
            <td>{{doctor.country}}</td>
            <td>{{doctor.clinic.name}}</td>
          </tr>
        </tbody>
      </table>       
    </div>
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
      let clinicID = this.$route.params.clinicID;
      let typeID = this.$route.params.typeID;
      let date = this.$route.params.date;

    httpClient
      .get("/users/doc/" + clinicID + "/" + typeID + "/" + date)
      .then(response => {
        this.doctors = _.cloneDeep(response.data);
      })
      .catch(error => {
        alert(error);
      });
  },
  methods: {
    redirectFurther: function(doctorID) {
      // alert(doctorID);

      let date = this.$route.params.date;
      this.$router.push("/patient/appointments/" + doctorID + "/" + date);
    }
  }
}
</script>