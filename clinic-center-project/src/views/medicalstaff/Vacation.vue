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
        medicalStaff: {
          id: "7",
          firstName: "Branko",
          lastName: "Coka"
        },
        accepted: false
      };

      httpClient
        .post("/vacs", req)
        .then(result => {
          this.start = result.data.startDate;
          this.$router.push("/doc");
        })
        .catch(err => {
          alert(err);
        });
    }
  }
};
</script>
