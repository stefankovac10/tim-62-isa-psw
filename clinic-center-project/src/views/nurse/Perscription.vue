<template>
  <div class="d-flex flex-row flex-wrap p-2 justify-content-center">
      <div class="card border-success mb-3" style="max-width: 20rem; max-height: 15rem; height:15rem; width:20rem; margin-right: 10px; margin-top:20px">
        <div class="card-body">
          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
          <button type="button" style="position: absolute; right: 0; bottom:0; margin: 10px" class="btn btn-success" v-on:click="certify">Certify</button>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  name: "perscription",
  data: function() {
    return {};
  }, 
  mounted(){

  },
  methods:{
      certify: function(){

      }
  }
};
</script>
