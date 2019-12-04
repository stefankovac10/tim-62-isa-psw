<template>
  <div class="d-flex p-2 justify-content-right">
    <form id="info" accept-charset="UTF-8" class="d-flex flex-column col-sm-2">
      <h1 class="p-2">Information</h1>

      <label class="p-2">Height</label>
      <input type="text" class="p-2" id="height" name="height" style = "width: 200px" v-model="height" v-bind:disabled=" mode == 'VIEW'"/>

      <label class="p-2">Width</label>
      <input type="text" class="p-2" id="width" name="width" v-model="width" style = "width: 200px" v-bind:disabled=" mode == 'VIEW'" />

      <label class="p-2">Blood type</label>
      <input type="text" class="p-2" id="bloodType" name="bloodType" v-model="bloodType" style = "width: 200px" v-bind:disabled=" mode == 'VIEW'" />
    
      <label class="p-2">Diopter </label>
      <input type="text" class="p-2" id="diopter" name="diopter" v-model="diopter" style = "width: 200px" v-bind:disabled=" mode == 'VIEW'"/>

      <br>
      <button class="btn btn-primary p-2" style = "width: 200px; margin:2px" v-if="mode == 'VIEW'" v-on:click.prevent="edit">Edit</button>
      <button class="btn btn-primary p-2" style = "width: 200px; margin:2px" v-if="mode == 'EDIT'" v-on:click.prevent="save">Save</button>
      <button class="btn btn-primary p-2" style = "width: 200px; margin:2px" v-if="mode == 'EDIT'" v-on:click.prevent="cancel">Cancel</button>
    </form>
    <div>
        <h1> History </h1> 
        <div class="overflow-auto" style="width: 350px; height: 580px; overflow-y: scroll;">       
            <div class="card border-success mb-3" style="max-width: 20rem;" v-for="examination in medicalRecord.examinations" v-bind:key="examination">
                    <div class="card-header">Dr. {{examination.doctor.lastName}}</div>
                    <div class="card-body">
                        <h4 class="card-title">{{examination.type}}</h4>
                        <p class="card-text">{{examination.report}}.</p>
                        <button type="button" class="btn btn-info" v-on:click="editReport">Edit</button>
                    </div>
            </div>
            <div class="card border-success mb-3" style="max-width: 20rem;">
                    <div class="card-header">Dr. Kovac</div>
                    <div class="card-body">
                        <h4 class="card-title">Ocni</h4>
                        <p class="card-text">{{this.report}}</p>
                        <button type="button" class="btn btn-info" v-on:click="editReport">Edit</button>
                    </div>
            </div>
        </div>
    </div>
    <div v-if="reportEdit === 'EDIT'">
        <h1>Edit Report</h1>
        <label for="exampleTextarea">Report</label>
        <textarea class="form-control" id="exampleTextarea" rows="3" v-model="report"></textarea>
        <br/>
        <button class="btn btn-primary p-2" v-on:click="saveReport()">Add</button>
        <button class="btn btn-primary p-2" v-on:click="cancel()">Cancel</button>
    </div>
        
  </div>
  
</template>

<script>
import { httpClient } from "@/services/Api.js";
export default {
  data: function() {
    return {
        height: undefined,
        width: undefined,
        bloodType: undefined,
        diopter: undefined,
        mode: 'VIEW',
        reportEdit: 'VIEW',
        medicalRecord: {},
        report: undefined,
        examination: {}
    };
  },
  mounted(){
      httpClient
        .get("/medicalrecord/1")
        .then(response => {
          this.medicalRecord = response.data;  
          this.height = this.medicalRecord.height;
          this.width = this.medicalRecord.width;
          this.diopter = this.medicalRecord.diopter;
          this.bloodType = this.medicalRecord.bloodType;    
        })
        .catch(error => {
          this.error = error;
        });

        httpClient
        .get("/examination/1")
        .then(response => {
          this.examination = response.data;  
          this.report = this.examination.report;   
        })
        .catch(error => {
          this.error = error;
        });
  },
  methods: {
    edit: function(){
        this.mode = 'EDIT';
    },
    save: function(){
        this.mode = 'VIEW';
        this.medicalRecord.diopter = this.diopter;
        this.medicalRecord.height = this.height;
        this.medicalRecord.width = this.width;
        this.medicalRecord.bloodType = this.bloodType;
        httpClient
        .put("/medicalrecord/",this.medicalRecord)
        .then(response => {
          this.medicalRecord = response.data;  
  
        })
        .catch(error => {
          this.error = error;
        });
    },
    cancel: function(){
        this.mode = 'VIEW';
        this.reportEdit = 'VIEW';
    },
    editReport: function(){
        this.reportEdit = 'EDIT';
    },
    saveReport: function(){
      this.reportEdit = 'VIEW';
      this.examination.report = this.report;
      httpClient
        .put("/examination/",this.examination)
        .then(response => {
          this.examination = response.data;  
  
        })
        .catch(error => {
          this.error = error;
        });
    }
  }
};
</script>
