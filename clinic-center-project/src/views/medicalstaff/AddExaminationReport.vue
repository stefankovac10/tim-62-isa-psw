<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Examination</h1>
      <br />
      <div>
      <label class="typo__label">Diagnosis</label>
      <multiselect v-model="diagnosis" :options="diagnosisis"  placeholder="Select one" label="name" track-by="name"></multiselect>
      </div>
      <div class="form-group">
      <br/>
        <label for="exampleTextarea">Report</label>
        <textarea class="form-control" id="exampleTextarea" rows="3" v-model="report"></textarea>
      <br/>
      <div>
        <label class="typo__label">Medicines</label>
          <multiselect v-model="medicinesSelected" tag-placeholder="Add this as new tag" placeholder="Search or add a tag" label="name" track-by="code" :options="medicines" :multiple="true" :taggable="true"></multiselect>
      </div>
      <button class="btn btn-primary p-2" v-on:click.prevent="add()">Add</button>
    </div>
    </form>
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
        diagnosisis: [],
        diagnosis: {},
        medicines: [],
        medicinesSelected: [],
        report: undefined,
        examination: {
          prescription: {
              medication: []
          }
        }
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
          if(this.diagnosis === null  || this.report === undefined || this.report === ''){
            this.$vToastify.info({
              body: "Please, fill the report and chose the diagnosis",
              title: "Warning",
              type: "warning",
              canTimeout: true,
              append: false
            });
            return;
          }else{
            
            this.examination.report = this.report;
            this.examination.prescription.medication = this.medicinesSelected;
            this.examination.diagnosis = this.diagnosis;
            this.examination.id = 1;
            
            httpClient
              .post("/examination/addReport", this.examination)
              .then(response => {
                this.response = response.data;
              })
              .catch(error => {
                this.error = error;
              });
            this.$vToastify.info({
              body: "Examination report has been saved",
              title: "Success",
              type: "success",
              canTimeout: true,
              append: false
            });
            this.$router.push("/doc/patients");
          }
      }
  },
};
</script>
<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>


