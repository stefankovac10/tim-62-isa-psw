<template>
  <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
    <div class=" flex-wrap p-2 justify-content-center" v-if="mode == 'VIEW'">
      <div class="d-flex p-2 justify-content-center flex-row flex-wrap">
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
      <div class="d-flex flex-wrap p-2 justify-content-center">
        <ul class="pagination">
        <!-- <li class="page-item disabled">
          <a class="page-link" href="#">&laquo;</a>
        </li>-->
        <li class="page-item active" v-for="i in pages" v-bind:key="i">
          <a class="page-link" v-on:click="nextPage(i - 1)">{{i}}</a>
        </li>
        <!-- <li class="page-item">
          <a class="page-link">&raquo;</a>
        </li>-->
        </ul>
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
import _ from "lodash";
export default {
  name: "listMedicaments",
  data: function() {
    return {
      name: undefined,
      code: undefined,
      description: undefined,
      mode: "VIEW",
      medications: undefined,
      pages:[]
    };
  },
  mounted(){
    this.mode="VIEW";

    httpClient
        .get("/medication/all/0")
        .then(response => {
          this.medications = _.cloneDeep(response.data);
          this.pages = [];
          for (let i = 1; i <= this.medications[0].pages; i++) {
            this.pages[i - 1] = i;
          }
        })
        .catch(error => {
          this.error = error;
          alert(error);
        });

  },
  methods: {
    nextPage: function(page) {
        httpClient
          .get("/medication/all/" + page)
          .then(response => {
            this.medications = _.cloneDeep(response.data);
            this.pages = [];
            if (this.medications[0].pages != undefined) {
              for (let i = 1; i <= this.medications[0].pages; i++) {
                this.pages[i - 1] = i;
              }
            }
          })
          .catch(error => {
            alert(error);
          });
    },
    refresh: function(){
        httpClient
          .get("/medication/all/0")
          .then(response => {
            this.medications = _.cloneDeep(response.data);
            this.pages = [];
            for (let i = 1; i <= this.medications[0].pages; i++) {
              this.pages[i - 1] = i;
            }
          })
          .catch(error => {
            this.error = error;
            alert(error);
          });
    },
    edit: function(medication) {
      this.mode = "EDIT";
      this.id = medication.id;
      this.name = medication.name;
      this.description = medication.description;
      this.code = medication.code;
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

      this.$vToastify.success({
        body: "Medication is edited",
        title: "Success",
        type: "success",
        canTimeout: true,
        append: false, duration: 2000
       });
    },
    remove: function(medication) {
      httpClient
        .delete("/medication/"+medication.id)
        .then(response => {
            this.response = response; 
            this.$vToastify.success({
              body: "Medication "+ medication.name + " is removed",
              title: "Success",
              type: "success",
              canTimeout: true,
              append: false, duration: 2000
            });
            this.refresh();
        })
        .catch(error => {
          this.error = error;
          this.$vToastify.error({
            body: "Medication "+ medication.name + " can't be deleted",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false, duration: 2000
          });
        });       
    }
  }
};
</script>
