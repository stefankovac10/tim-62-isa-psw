<template>
  <div class="jumbotron">
    <h1 class="display-3">Congraulation</h1>
    <p class="lead">You have been succesfully registrated as a patient.</p>
    <hr class="my-4">
    <p>Please, go to sign up page by clicking on button below.</p>
    <p class="lead">
        <a class="btn btn-primary btn-lg" href="#" role="button" v-on:click="login">Sign up</a>
    </p>
</div>
</template>

<script>

export default {
  data: function() {
    return {
      
    };
  },
  methods: {
   login: function(){
       this.$router.push("/login");
   }   
  }
};
</script>
