<template>
  <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
    <div class="d-flex justify-content-center flex-wrap flex-column p-2">
      <h1>Patient's info:</h1>
      <p>Name: {{ patient.firstName }} {{ patient.lastName}}</p>
      <p>JMBG: {{ patient.jmbg }}</p>
      <p>Telephone: {{ patient.telephone }}</p>
      <p>Address: {{ patient.address }}, {{ patient.city }}, {{ patient.country }}</p>
      <p>Email: {{ patient.email }}</p>
      <button type="button" class="btn btn-danger" style="width: 150px" v-on:click="goMedicalRecord">Medical record</button>
    </div>
     <div id="results" class="d-flex flex-column flex-wrap p-2 justify-content-center" style="width: 500px; margin: 15px; margin-top:0px">
       <div><h1>Examinations: </h1></div>
        <table class="table table-hover" > 
          <thead>
            <tr>
              <th scope="col">Type</th>
              <th scope="col">Doctor</th>
              <th scope="col">Date</th>
              <th scope="col">Duration</th>
              <th scope="col">Room</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr class="table-primary" v-for="examination in examinations" v-bind:key="examination.id">
              <th scope="row">{{examination.type.name}}</th>
              <td>{{examination.doctor.firstName}} {{examination.doctor.lastName}}</td>
              <td>{{examination.dateMoment}}</td>
              <td>{{examination.duration/60000}} min</td>
              <td>{{examination.examinationRoom.number}}</td>
              <td><button v-if="role === 'ROLE_DOCTOR' && user.id === examination.doctor.id  && examination.report == null"  class="btn btn-success" v-on:click="startExamination(examination.id)">Start examination</button></td>
            </tr>
          </tbody>
        </table>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import moment from "moment";
export default {
  data: function() {
    return {
      user: undefined,
      role: undefined,
      examinations: undefined,
      id: undefined,
      patient: undefined
    };
  },
  mounted(){
    this.role=localStorage.getItem('Authority');
    this.id = this.$route.params.id;
    httpClient
      .get("/patient/" + this.id)
      .then(response => {
        this.patient = response.data;
      })
      .catch(() => {
        
      });

    httpClient
      .get("/users/mail/" + localStorage.getItem('Email'))
      .then(response => {
        this.user = response.data;
      })
      .catch(() => {
        
      });

    httpClient
      .get("/patient/examination/" + this.id)
      .then(response => {
        this.examinations = response.data;
        for (const exam of this.examinations) {
              exam.dateMoment = moment(exam.date).format(
                "dddd, MMMM Do YYYY, h:mm:ss"
              );
        }
      })
      .catch(() => {
        
      });

  },
  methods:{
    goMedicalRecord: function(){
      this.$router.push("/doc/editMedicalRecord/"+this.id)
    },
    startExamination: function(id){
          httpClient
            .get("/examination/check/"+ id)
                .then(() => {
                   this.$router.push('/doc/addexaminationreport/'+id+'/'+this.id);
                })
                .catch(() => {
                    this.$vToastify.info({
                    body: "Examination can't start, it's not time for that examination",
                    title: "Warning",
                    type: "warning",
                    canTimeout: true,
                    append: false, duration: 2000
                 });
                });
          
      }
  }
};
</script>
