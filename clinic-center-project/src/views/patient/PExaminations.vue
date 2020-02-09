<template>
  <div class="d-flex p-2 justify-content-center">
    <h1>History of examinations</h1>
  </div>
</template>

<script>
import { httpClient } from "@/services/Api.js";
import _ from "lodash";

export default {
  data: function() {
    return {
      examinations: []
    };
  },
  mounted() {
      var email = localStorage.getItem("Email");

      httpClient
        .get("/examination/patient/" + email)
        .then(response => {
        this.examinations = _.cloneDeep(response.data);
      })
      .catch(error => {
        alert(error);
      });
  }
};
</script>
