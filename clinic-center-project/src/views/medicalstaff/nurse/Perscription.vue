<template>
  <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
    <label v-if="prescriptions.length === 0 || filteredPrescriptions.length ===  0" ><h3>Currently there are no new prescriptions</h3></label>
      <div class="card border-success mb-3" style="max-width: 20rem; max-height: 15rem; height:15rem; width:20rem; margin-right: 10px; margin-top:20px"
       v-for = "p in filteredPrescriptions" v-bind:key="p.id">
       <div>Doctor: {{p.doctor.firstName}} {{p.doctor.lastName}}</div><br/>
          <div class="card-body"> 
            <p class="card-text" v-for = "m in p.medication" v-bind:key = "m.id">{{m.name}}</p>
            <button type="button" style="position: absolute; right: 0; bottom:0; margin: 10px" class="btn btn-success" v-on:click="certify(p.id)">Certify</button>
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
      prescriptions: undefined,
      prescription: {},
      medication: [],    
    };
  }, 
  mounted(){
      httpClient
        .get("/prescription/all/"+localStorage.getItem("Email"))
        .then(response => {
          this.prescriptions = response.data;  
          this.medication = this.prescription.medication;
        })
        .catch(error => {
          this.error = error;
        });
  },
  computed: {
    filteredPrescriptions: function () {
      return this.prescriptions.filter(function (p) {
        return p.certified === false || p.certified === null;
    })
     }
  },
  methods:{
      refresh: function(){
          httpClient
            .get("/prescription/all/"+localStorage.getItem("Email"))
            .then(response => {
              this.prescriptions = response.data;  
              this.medication = this.prescription.medication;
            })
            .catch(error => {
              this.error = error;
            });
      },
      certify: function(id){
          httpClient
        .get("/prescription/certify/"+id+"/" +localStorage.getItem("Email"))
        .then(response => {
          this.prescription = response.data;      
          this.refresh();
        })
        .catch(error => {
          this.error = error;
        });
        this.$vToastify.info({
              body: "Prescription is succesfully certified",
              title: "Success",
              type: "success",
              canTimeout: true,
              append: false, duration: 2000
            });  
      }
  }
};
</script>
