<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Add report</h1>
      <br />
      <div class="form-group">
        <label for="exampleSelect1">Diagnosis</label>
        <select class="form-control" id="exampleSelect1" v-model="diagnosis">
          <option v-for="diagnosis in diagnosisis" :key="diagnosis">{{diagnosis.name}}</option>
        </select>
      </div>
      <br/>
      <div class="form-group">
        <label for="exampleSelect1">Medicines</label>
        <select class="form-control" id="exampleSelect1" v-model="medicine">
          <option v-for="medicine in medicines" :key="medicine">{{medicine.name}}</option>
        </select>
      </div>
      <div class="form-group">
      <br/>
      <label for="exampleTextarea">Report</label>
      <textarea class="form-control" id="exampleTextarea" rows="3" v-model="report"></textarea>
      <br/>
      <button class="btn btn-primary p-2" v-on:click="add">Add</button>
    </div>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
export default {
  data: function() {
    return {
        diagnosisis: {},
        medicines: {},
        report: undefined
    };
  },
  mounted(){
      httpClient
      .get("/diagnosis/all")
      .then(response => {
        this.diagnosisis = response.data;
      })
      .catch(error => {
        this.error = error;
      });

      httpClient
      .get("/medication/all")
      .then(response => {
        this.medicines = response.data;
      })
      .catch(error => {
        this.error = error;
      });
  },
  methods: {
      add: function(){
          httpClient
            .post("/examination",{
                report: this.report,
                medicine: this.medicine,
                diagnosis: this.diagnosis
            })
            .then(response => {
                this.response = response.data;
            })
            .catch(error => {
                this.error = error;
            });
      }
  },
};
</script>


