<template>
  <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
    <div v-if="mode == 'VIEW'">
      <div
        class="card border-primary mb-3"
        style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px"
        v-for="medication in medications" :key="medication"
      >
        <div class="card-header">{{medication.code}}</div>
        <div class="card-body">
          <h4 class="card-title">{{medication.name}}</h4>
          <p class="card-text">
            {{medication.description}}
          </p>
          <button type="button" class="btn btn-primary" v-on:click="edit(medication)" style="margin-right:10px">Edit</button>
          <button type="button" class="btn btn-danger" v-on:click="remove(medication)">Delete</button>
        </div>
      </div>
    </div>
    <div v-else>
      <form>
        <fieldset>
          <legend>Change medicament</legend>
          <div class="form-group">
            <label for="exampleInputEmail1">Name</label>
            <input
              type="text"
              class="form-control"
              id="nameClinic"
              v-model="name"
              placeholder="Enter name"
            />
          </div>
          <div class="form-group">
            <label for="exampleInputEmail1">Code</label>
            <input
              type="text"
              class="form-control"
              id="code"
              v-model="code"
              placeholder="Enter code"
            />
          </div>
          <div class="form-group">
            <label for="exampleTextarea">Description</label>
            <textarea class="form-control" id="descriptionClinic" v-model="description" rows="3"></textarea>
          </div>
          <button type="submit" class="btn btn-primary" v-on:click.prevent="save" style="margin-right:10px">Save</button>
          <button type="submit" class="btn btn-danger" v-on:click.prevent="cancel">Cancel</button>
        </fieldset>
      </form>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
export default {
  name: "listMedicaments",
  data: function() {
    return {
      name: undefined,
      code: undefined,
      description: undefined,
      mode: "VIEW",
      medications: {}
    };
  },
  mounted(){
    this.mode="VIEW";

    httpClient
    .get("/medication/all")
    .then(response => {
      this.medications = response.data;      
    })
    .catch(error => {
      this.error = error;
    });

  },
  methods: {
    refresh: function(){
        httpClient
        .get("/medication/all")
        .then(response => {
          this.medications = response.data;      
        })
        .catch(error => {
          this.error = error;
        });
    },
    edit: function(diagnosis) {
      this.mode = "EDIT";
      this.id = diagnosis.id;
      this.name = diagnosis.name;
      this.description = diagnosis.description;
      this.code = diagnosis.code;
    },
    cancel: function(){
      this.id = undefined;
      this.name = undefined;
      this.description = undefined;
      this.code = undefined;
      this.mode = "VIEW";
    },
    save: function() {
      this.mode = "VIEW";
      httpClient
        .put("/medication", {"id":this.id, "name":this.name, "description":this.description, "code":this.code})
        .then(response => {
            this.response = response; 
            this.refresh();
        })
        .catch(error => {
          this.error = error;
        });

      this.$vToastify.info({
        body: "Medication is edited",
        title: "Success",
        type: "success",
        canTimeout: true,
        append: false
       });
    },
    remove: function(medication) {
      httpClient
        .delete("/medication/"+medication.id)
        .then(response => {
            this.response = response; 
            this.refresh();
        })
        .catch(error => {
          this.error = error;
        });

        this.$vToastify.info({
        body: "Medication "+ medication.name + " is removed",
        title: "Success",
        type: "success",
        canTimeout: true,
        append: false
       });
    }
  }
};
</script>
