<template>
  <div class="d-flex p-2 justify-content-center">
    <form>
      <fieldset>
        <legend>Add Diagnosis</legend>
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
          <textarea
            class="form-control"
            id="descriptionClinic"
            v-model="description"
            rows="3"
          ></textarea>
        </div>
        <button type="submit" class="btn btn-primary" v-on:click="add">Add</button>
      </fieldset>
    </form>
  </div>
</template>

<script>
export default {
  name: "addClinic",
  data: function() {
    return {
      name: undefined,
      code: undefined,
      description: undefined
    };
  }, 
  methods : {
    add : function(){
        if(this.name === undefined || this.name === '' || this.code ===  undefined || this.code === '' || this.description === undefined || this.description === ''){
        alert("All field must be filled");
        return;
      }
    }
  }
};
</script>
