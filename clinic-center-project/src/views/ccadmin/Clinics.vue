<template>
  <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
    <div class=" flex-wrap p-2 justify-content-center" v-if="mode == 'VIEW'">
      <div class="d-flex p-2 justify-content-center flex-row flex-wrap">
        <div 
          class="card border-primary mb-3"
          style="max-width: 20rem; max-height: 18rem; float: left; margin: 10px" 
          v-for="clinic in clinics" :key="clinic.id"
        >
          <div class="card-header">{{clinic.address}}</div>
          <div class="card-body">
            <h4 class="card-title">{{clinic.name}}</h4>
            <p class="card-text">
              {{clinic.description}}
            </p>
            <p class="card-text">
            Grade: {{clinic.grade}}
            </p>
            <p class="card-text">
              Income: {{clinic.income}}
            </p>
            <button type="button" class="btn btn-primary" v-on:click="edit(clinic)" style="margin-right:10px">Edit</button>
            <button type="button" class="btn btn-danger" v-on:click="remove(clinic)">Delete</button>
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
          <legend>Change clinic</legend>
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
            <label for="exampleInputEmail1">Address</label>
            <input
              type="text"
              class="form-control"
              id="addressClinic"
              v-model="address"
              placeholder="Enter address"
            />
          </div>
          <div class="form-group">
            <label for="exampleTextarea">Description</label>
            <textarea class="form-control" id="descriptionClinic" v-model="description" rows="3"></textarea>
          </div>
          <button type="submit" class="btn btn-primary" v-on:click="save" style="margin-right:10px">Save</button>
          <button type="submit" class="btn btn-danger" v-on:click="cancel">Cancel</button>
        </fieldset>
      </form>
    </div>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";
export default {
  name: "clinics",
  data: function() {
    return {
      id: undefined,
      name: undefined,
      address: undefined,
      description: undefined,
      mode: "VIEW",
      clinics: undefined,
      pages: []
    };
  },
  mounted(){
      this.mode="VIEW";

       httpClient
        .get("/clinics/all/0")
        .then(response => {
          this.clinics = _.cloneDeep(response.data);
          this.pages = [];
          for (let i = 1; i <= this.clinics[0].pages; i++) {
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
          .get("/clinics/all/" + page)
          .then(response => {
            this.clinics = _.cloneDeep(response.data);
            this.pages = [];
            if (this.clinics[0].pages != undefined) {
              for (let i = 1; i <= this.clinics[0].pages; i++) {
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
          .get("/clinics/all/0")
          .then(response => {
            this.clinics = _.cloneDeep(response.data);
            this.pages = [];
            for (let i = 1; i <= this.clinics[0].pages; i++) {
              this.pages[i - 1] = i;
            }
          })
          .catch(error => {
            this.error = error;
            alert(error);
          });
    },
    edit: function(clinic) {
      this.mode = "EDIT";
      this.id = clinic.id;
      this.name = clinic.name;
      this.description  = clinic.description;
      this.address = clinic.address;
    },
    cancel: function(){
      this.id = undefined;
      this.name = undefined;
      this.description = undefined;
      this.address = undefined;
      this.mode = "VIEW";
    },
    save: function() {
      this.mode = "VIEW";
      httpClient
        .put("/clinics", {"id":this.id, "name":this.name, "address":this.address, "description":this.description})
        .then(response => {
            this.response = response; 
            this.refresh();
        })
        .catch(error => {
          this.error = error;
        });
        this.$vToastify.info({
              body: "Clinic has been edited",
              title: "Success",
              type: "success",
              canTimeout: true,
              append: false, duration: 2000
        });
    },
    remove: function(clinic) {
        httpClient
          .delete("/clinics/"+clinic.id)
          .then(response => {
              this.response = response; 
              this.refresh();
          })
          .catch(error => {
            this.error = error;
          });
        this.$vToastify.info({
              body: "Clinic " + clinic.name + " has been deleted",
              title: "Success",
              type: "success",
              canTimeout: true,
              append: false, duration: 2000
        });
    }
  }
};
</script>
