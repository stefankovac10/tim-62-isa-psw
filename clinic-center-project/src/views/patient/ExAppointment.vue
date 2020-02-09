<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1>Schedule an examination appointment</h1>
      <br />
    <!--
      <label class="p-2">Name</label>
      <input type="text" class="p-2" id="name" name="name" v-model="name" />

      <label class="p-2">Address</label>
      <input type="text" class="p-2" id="address" name="address" v-model="address" />

      <label class="p-2">Description</label>
      <input type="text" class="p-2" id="description" name="description" v-model="description" />
    -->
      <label class="p-2">Date</label>
      <input type="date" class="p-2" id="date" name="date" v-model="date" />

      <label class="p-2">Type of examination</label>
      <select class="p-2" name="typeOfExamination" id="type" v-model="type">
        <option v-for="t in types" v-bind:key="t.key">{{t.name}}</option>
      </select>
    <!--
      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="search">Search</button>
      <br />
    -->
      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="search2">Search</button>
      <br />
    </form>

    <table class="table table-hover" id="clinicsTable">
      <thead>
        <tr>
          <th scope="col" v-on:click="sortTable(0)">Name</th>
          <th scope="col" v-on:click="sortTable(1)">Address</th>
          <th scope="col" v-on:click="sortTable(2)">Description</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="clinic in clinics" :key="clinic.id" class="table-primary" v-on:click="sendToListOdClinicsDoctors(clinic.id, clinic.specializedTypeID, date)">
          <td>{{clinic.name}}</td>
          <td>{{clinic.address}}</td>
          <td>{{clinic.description}}</td>
        </tr>
      </tbody>
    </table> 
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
// import _ from "lodash";

export default {
  data: function() {
    return {
      // name: "",
      // address: "",
      // description: "",
      date: undefined,
      type: undefined,
      types: undefined,
      clinics: []
    };
  },
  mounted() {
    /*
    httpClient
      .get("/clinics/all")
      .then(response => {
        this.clinics = _.cloneDeep(response.data);
      })
      .catch(error => {
        alert(error);
      });
    */
    httpClient
        .get("/types/all")
        .then(response => {
          this.types = response.data;
        })
        .catch(error => {
          error;
        });
  },
  methods: {
    search: function() {
      httpClient
        .post("/clinics/search", {
            name: this.name,
            address: this.address,
            description: this.description
        })
        .then(response => {
          this.clinics = response.data;
        })
        .catch(error => {
          this.error = error;
        });
    },
    search2: function() {
      /*
      if (this.isSelected(this.date) && this.isSelected(this.type)) {
        let d = "", t = "";

        if(!this.date) { d = "_" } else { d = this.date; }
        if(!this.type) { t = "_" } else { t = this.type; }

        httpClient
          .get("/clinics/search2/" + d + "/" + t)
          .then(response => {
            this.clinics = response.data;
          })
          .catch(error => {
            this.error = error;
          });
      } else {
        alert("mamma mia");
      }
      */

      let d = "", t = "";

        if(!this.date) { d = "_" } else { d = this.date; }
        if(!this.type) { t = "_" } else { t = this.type; }

        httpClient
          .get("/clinics/search2/" + d + "/" + t)
          .then(response => {
            this.clinics = response.data;
          })
          .catch(() => {
            this.$vToastify.info({
              body: "You must select both date and type.",
              title: "Warning",
              type: "warning",
              canTimeout: true,
              append: false,
              duration: 2000
            });
          });
    },
    sendToListOdClinicsDoctors: function(clinicID, typeID, date) {
        this.$router.push("/patient/doctors/" + clinicID + "/" + typeID + "/" + date);
    },
    isSelected: function(element) {
      if (!element) { return false; } else { return true; }
    },
    sortTable: function(n) {
      var table, rows, switching, i, x, y, shouldSwitch, direction, switchcount = 0;

      table = document.getElementById("clinicsTable");
      switching = true;
      direction = "asc";  // set the sorting direction to ascending

      while (switching) { // make a loop that will continue until no switching has been done
        switching = false;  // start by saying: no switching is done
        rows = table.rows;

        // loop through all table rows (except the first one, which contains table headers)
        for (i = 1; i < (rows.length - 1); i++) {
          shouldSwitch = false; //  start by saying there should be no switching

          // get the two elements you want to compare, one from current row and one from the next
          x = rows[i].getElementsByTagName("TD")[n];
          y = rows[i + 1].getElementsByTagName("TD")[n];

          // check if the two rows should switch place, based on the direction, asc or desc
          if (direction == "asc") {
            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
              // if so, mark as a switch and break the loop:
              shouldSwitch = true;
              break;
            }
          } else if (direction == "desc") {
            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
              // if so, mark as a switch and break the loop:
              shouldSwitch = true;
              break;
            }
          }
        }

        if (shouldSwitch) {
          // if a switch has been marked, make the switch and mark that a switch has been done
          rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
          switching = true;
          switchcount ++;  // each time a switch is done, increase this count by 1    
        } else {
          /*If no switching has been done AND the direction is "asc",
          set the direction to "desc" and run the while loop again.*/
          if (switchcount == 0 && direction == "asc") {
            direction = "desc";
            switching = true;
          }
        }
      }
    }
  }
};
</script>