<template>
  <div class="d-flex p-2 justify-content-center">
    <form id="login" accept-charset="UTF-8" class="d-flex flex-column col-sm-4">
      <h1 class="p-2">Vacation request</h1>
      <br />

      <label class="p-2">Start of vacation</label>
      <input type="date" class="p-2" id="start" name="start" v-model="start" />

      <label class="p-2">End of vacation</label>
      <input type="date" class="p-2" id="finish" name="finish" v-model="finish" />

      <br />
      <button class="btn btn-primary p-2" v-on:click.prevent="add">Submit</button>
    </form>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";

export default {
  data: function() {
    return {
      start: undefined,
      finish: undefined
    };
  },
  mounted() {
    httpClient
      .get("/medicalStaff/mail/" + localStorage.getItem("Email"))
      .then(response => {
        this.medicalStaff = response.data;
      })
      .catch(error => {
        this.$vToastify.error({
          body: "Error getting medical staff",
          title: error,
          type: "error",
          canTimeout: true,
          append: false,
          duration: 2000
        });
      });
  },
  methods: {
    add: function() {
      var startDate = new Date(this.start);
      // var month = ("0" + (startDate.getMonth() + 1)).slice(-2);
      // var day = ("0" + startDate.getDate()).slice(-2);
      // var year = startDate.getFullYear();
      // var startTrans = day + "-" + month + "-" + year;
      var endDate = new Date(this.finish);
      // month = ("0" + (endDate.getMonth() + 1)).slice(-2);
      // day = ("0" + endDate.getDate()).slice(-2);
      // year = endDate.getFullYear();
      // var finishTrans = day + "-" + month + "-" + year;

      var req = {
        startDate: startDate,
        endDate: endDate,
        medicalStaff: this.medicalStaff,
        accepted: false
      };

      httpClient
        .post("/vacs", req)
        .then(result => {
          this.start = result.data.startDate;
          this.$vToastify.success({
            body: "Requested vacation",
            title: "Success",
            type: "success",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        })
        .catch(() => {
          this.$vToastify.error({
            body: "Error adding request for medical staff",
            title: "Error",
            type: "error",
            canTimeout: true,
            append: false,
            duration: 2000
          });
        });
    }
  }
};
</script>
