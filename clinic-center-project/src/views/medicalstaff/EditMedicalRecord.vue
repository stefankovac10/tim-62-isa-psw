<template>
  <div class="d-flex p-2 justify-content-right">
    <form id="info" accept-charset="UTF-8" class="d-flex flex-column col-sm-2">
      <h1 class="p-2">Information</h1>

      <label class="p-2">Height (cm)</label>
      <input type="text" class="p-2" id="height" name="height" style = "width: 200px" v-model="height" v-bind:disabled=" mode == 'VIEW'"/>

      <label class="p-2">Weight (kg)</label>
      <input type="text" class="p-2" id="weight" name="weight" v-model="weight" style = "width: 200px" v-bind:disabled=" mode == 'VIEW'" />

      <label class="p-2">Blood type</label>
      <input type="text" class="p-2" id="bloodType" name="bloodType" v-model="bloodType" style = "width: 200px" v-bind:disabled=" mode == 'VIEW'" />
    
      <label class="p-2">Diopter</label>
      <input type="text" class="p-2" id="diopter" name="diopter" v-model="diopter" style = "width: 200px" v-bind:disabled=" mode == 'VIEW'"/>
      
      <br>
      <button class="btn btn-primary p-2" style = "width: 200px; margin:2px" v-if="mode == 'VIEW' && role === 'ROLE_DOCTOR'" v-on:click.prevent="edit">Edit</button>
      <button class="btn btn-primary p-2" style = "width: 200px; margin:2px" v-if="mode == 'EDIT'" v-on:click.prevent="save">Save</button>
      <button class="btn btn-primary p-2" style = "width: 200px; margin:2px" v-if="mode == 'EDIT'" v-on:click.prevent="cancel">Cancel</button>
    </form>
    <div style="margin-top:10px; margin-left: 50px">
        <h1> History </h1> 
        <div class="overflow-auto" style="width: 400px; height: 580px; overflow-y: scroll;">       
            <div class="card border-success mb-3" style="max-width: 20rem;" v-for="examination in this.examinations" v-bind:key="examination.id">
                    <div class="card-header">Dr.  {{examination.doctor.firstName}}  {{examination.doctor.lastName}} </div>
                    <div class="card-body">
                        <h4 class="card-title">Type: {{examination.type.name}}</h4>
                        <p class="card-text">Report: {{examination.report}}.</p>
                        <p class="card-text">Diagnosis: {{examination.diagnosis.name}}.</p>
                        <button type="button" class="btn btn-info" v-if="role === 'ROLE_DOCTOR' && examination.doctor.id === user.id " v-on:click="editReport(examination)">Edit</button>
                    </div>
            </div>
        </div>
    </div>
    <div v-if="role === 'ROLE_DOCTOR'" style="width: 400px ; margin-top: 10px; margin-left: 50px">
        <h1>Edit Report</h1>
        <label for="exampleTextarea">Report</label>
        <textarea v-bind:disabled="reportEdit === 'VIEW'" class="form-control" id="exampleTextarea" rows="3" v-model="report"></textarea>
        <br/>
        <label class="typo__label">Diagnosis</label>
        <multiselect v-bind:disabled="reportEdit === 'VIEW'" v-model="diagnosis" :options="diagnosisis"  placeholder="Select one" label="name" track-by="name"></multiselect>
        <button v-if = "reportEdit === 'EDIT'" class="btn btn-primary p-2" style = "margin-top: 10px; margin-right: 10px" v-on:click="saveReport()">Save</button>
        <button v-if = "reportEdit === 'EDIT'" class="btn btn-primary p-2" style = "margin-top: 10px;" v-on:click="cancel()">Cancel</button>
    </div>
    <button v-if="role === 'ROLE_DOCTOR'" type="button" style="position: absolute; right: 0; bottom:0; margin: 35px" class="btn btn-success" v-on:click="addExamReport()">Add Examination Report</button>
  </div>
  
</template>

<script>
import { httpClient } from "@/services/Api.js";
import Multiselect from 'vue-multiselect'
export default {
  components:{
       Multiselect
  },
  data: function() {
    return {
        user: undefined,
        role: undefined,
        patient: undefined,
        id: undefined,
        mode: 'VIEW',
        reportEdit: 'VIEW',
        height: undefined,
        weight: undefined,
        bloodType: undefined,
        diopter: undefined,
        report: undefined,
        examinations: [],
        examination: {},
        medicalRecord: undefined,
        diagnosisis: [],
        diagnosis: {},
    };
  },
  mounted(){
      this.role = localStorage.getItem("Authority");
      this.report = undefined;
      this.diagnosis = undefined;
      this.reportEdit = 'VIEW';

      httpClient
        .get("/diagnosis/all")
        .then(response => {
          this.diagnosisis = response.data;
        })
        .catch(error => {
          this.error = error;
        })

      httpClient
        .get("/users/mail/"+localStorage.getItem('Email'))
        .then(response =>{
          this.user = response.data;
        })
        .catch(error => {
            this.error = error;
          });

      if(this.role === 'ROLE_DOCTOR'){
          this.id = this.$route.params.id;
          httpClient
            .get("/patient/" + this.id)
            .then(response => {
              this.patient = response.data;
              httpClient
                .get("/medicalrecord/"+this.patient.medicalRecord.id)
                .then(response => {
                  this.medicalRecord = response.data; 
                  this.height = this.medicalRecord.height;
                  this.weight = this.medicalRecord.weight;
                  this.diopter = this.medicalRecord.diopter;
                  this.bloodType = this.medicalRecord.bloodType;   
                  this.examinations = this.medicalRecord.examinations; 
                })
                .catch(error => {
                  this.error = error;
                });
            })
            .catch(error => {
              alert(error);
            });    
      }else if(this.role === 'ROLE_PATIENT'){
          httpClient
            .get("/patient/get/" +localStorage.getItem('Email'))
            .then(response => {
              this.patient = response.data;
              httpClient
                .get("/medicalrecord/"+this.patient.medicalRecord.id)
                .then(response => {
                  this.medicalRecord = response.data; 
                  this.height = this.medicalRecord.height;
                  this.weight = this.medicalRecord.weight;
                  this.diopter = this.medicalRecord.diopter;
                  this.bloodType = this.medicalRecord.bloodType;   
                  this.examinations = this.medicalRecord.examinations; 
                })
                .catch(error => {
                  this.error = error;
                });
            })
            .catch(error => {
              alert(error);
            });
      }        
  },
  methods: {
    refresh: function(){
      this.role = localStorage.getItem("Authority");
      this.report = undefined;
      this.diagnosis = undefined;
      this.reportEdit = 'VIEW';

      httpClient
        .get("/diagnosis/all")
        .then(response => {
          this.diagnosisis = response.data;
        })
        .catch(error => {
          this.error = error;
        })

      httpClient
        .get("/users/mail/"+localStorage.getItem('Email'))
        .then(response =>{
          this.user = response.data;
        })
        .catch(error => {
            this.error = error;
          });

    this.id = this.$route.params.id;
      httpClient
        .get("/patient/" + this.id)
        .then(response => {
          this.patient = response.data;
          httpClient
            .get("/medicalrecord/"+this.patient.medicalRecord.id)
            .then(response => {
              this.medicalRecord = response.data; 
              this.height = this.medicalRecord.height;
              this.weight = this.medicalRecord.weight;
              this.diopter = this.medicalRecord.diopter;
              this.bloodType = this.medicalRecord.bloodType;   
              this.examinations = this.medicalRecord.examinations; 
            })
            .catch(error => {
              this.error = error;
            });
        })
        .catch(error => {
          alert(error);
        });   
    },
    edit: function(){
        this.mode = 'EDIT';
    },
    save: function(){
        this.mode = 'VIEW';
        this.medicalRecord.diopter = this.diopter;
        this.medicalRecord.height = this.height;
        this.medicalRecord.weight = this.weight;
        this.medicalRecord.bloodType = this.bloodType;
        httpClient
        .put("/medicalrecord/",this.medicalRecord)
        .then(response => {
          this.medicalRecord = response.data;   
        })
        .catch(error => {
          this.error = error;
        });
        this.$vToastify.info({
          body: "Information about patient has been saved",
          title: "Success",
          type: "success",
          canTimeout: true,
          append: false, duration: 2000
        });
    },
    cancel: function(){
        this.mode = 'VIEW';
        this.reportEdit = 'VIEW';
        this.report = undefined;
        this.diagnosis = undefined;
    },
    editReport: function(examination){
        this.reportEdit = 'EDIT';
        this.report = examination.report;
        this.diagnosis = examination.diagnosis;
        this.examination.id = examination.id;
    },
    saveReport: function(){
      this.examination.report = this.report;
      this.examination.diagnosis = this.diagnosis;
      httpClient
        .put("/examination",this.examination)
        .then(response => {
          this.examination = response.data;
          this.refresh();       
        })
        .catch(error => {
          this.error = error;
        });
        this.report = undefined;
        this.diagnosis = undefined;
        this.reportEdit = 'VIEW';
      this.$vToastify.info({
        body: "Changes on examination report have been saved",
        title: "Success",
        type: "success",
        canTimeout: true,
        append: false, duration: 2000
      });
    },
    addExamReport: function(){
      this.$router.push("/doc/addexaminationreport");
    }
  }
};
</script>
