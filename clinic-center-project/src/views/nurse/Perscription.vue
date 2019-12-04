<template>
  <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
      <div class="card border-success mb-3" style="max-width: 20rem; max-height: 15rem; height:15rem; width:20rem; margin-right: 10px; margin-top:20px"
       v-for = "prescription in prescriptions" v-bind:key="prescription">
        <div v-if="prescription.certified === false">
          <div>Doctor: {{prescription.doctor.firstName}} {{prescription.doctor.lastName}}</div>
          <div class="card-body">
            <p class="card-text" v-for = "medication in prescription.medication" v-bind:key = "medication">{{medication.name}}</p>
            <button type="button" style="position: absolute; right: 0; bottom:0; margin: 10px" class="btn btn-success" v-on:click="certify(prescription.id)">Certify</button>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
export default {
  name: "perscription",
  data: function() {
    return {
      prescriptions: {},
      prescription:{}
    };
  }, 
  mounted(){
      httpClient
        .get("/prescription/all")
        .then(response => {
          this.prescriptions = response.data;      
        })
        .catch(error => {
          this.error = error;
        });
  },
  methods:{
      refresh: function(){
          httpClient
          .get("/prescription/all")
          .then(response => {
            this.prescriptions = response.data;      
          })
          .catch(error => {
            this.error = error;
          });
      },
      certify: function(id){
          httpClient
        .get("/prescription/certify/"+id)
        .then(response => {
          this.prescription = response.data;      
          this.refresh();
        })
        .catch(error => {
          this.error = error;
        });
        
      }
  }
};
</script>
