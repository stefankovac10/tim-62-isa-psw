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
      httpClient
        .post("/vacs", {
          startDate: this.start,
          endDate: this.finish
        })
        .then(result => {
          this.start = result.data.startDate;
          this.$router.push("/doc");
        })
        .catch(err => {
          alert(err.response.data);
        });
    }
  }
};
</script>
