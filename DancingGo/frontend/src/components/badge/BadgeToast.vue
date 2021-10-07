<template>
  <div>
    <!-- <v-img id="background" src="images/home/home_background.jpg"></v-img> -->
    <b-button @click="showToast">Show Toast with custom content</b-button>
    <b-button @click="$bvToast.show('my-toast')">ssss</b-button>

   <b-toast id="my-toast"
    toaster='b-toaster-top-center'
    append-toast="true"
    body-class="toast_body"
    header-class="toast_header"

    toast-class="toast_toast"
    no-close-button
    >
      <template #toast-title>
        <!-- <div class="d-flex flex-grow-1 align-items-baseline mr-2">
          <b-img blank blank-color="#ff5555" class="mr-2" width="12" height="12"></b-img>
          <strong class="mr-2">Notice!aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</strong>
          <small class="text-muted ml-auto">42 seconds ago</small>
        </div> -->
      </template>
      <div class="d-flex justify-content-center badge_content">
        <!-- <div class="mr-5">
        </div> -->
        <b-spinner class="mt-3" type="grow" style="width: 3rem; height: 3rem;"/>
        <div class="mr-5">
        </div>
        <!-- <img src="images/badgeImg/1001.png" width="100vw"> -->
        <img :src="badgeImg" width="100vw">
        <div class="mr-5">
        </div>
        <div class="mt-4">
        <h3><b>{{badgeName}}</b></h3>
        </div>
        <div class="mr-5">
        </div>
        <b-spinner class="mt-3" type="grow" style="width: 3rem; height: 3rem;"/>
      </div>
    </b-toast>
  </div>
</template>

<script>
  export default {
    name : 'BadgeToast',
    props:{
      //user_seq: Number,
      badgeNum: Number,
      badgeName: String,
      badgeImg: String
    },
    data() {
      return {
        count: 0,
        // badgeNum : 1001,
        // badgeName : "시작이 반이다!",
        // badgeImg : "images/badgeImg/1001.png",
      }
    },
    mounted(){
      this.$bvToast.show('my-toast')
    },
    methods: {
      showToast() {
        // Use a shorter name for this.$createElement
        const h = this.$createElement
        // var im = this.$$createElement("img")
        // im.src="images/badgeImg/1001.png"
        // Increment the toast count
        this.count++
        // Create the message
        const vNodesMsg = h(
          'div',
          { class: ['d-flex', 'flex-grow-1', 'align-items-baseline', 'mr-2'] },
          [
            h('img', { src: 'images/badgeImg/1001.png' }),
            h('small', { class: 'ml-auto text-italics' }, '5 minutes ago')
          ]
          )
        // const vNodesMsg = h(
        //   // 'p',
        //   // { class: ['text-center', 'mb-0'] },
        //   // [
        //   //   h('b-spinner', { props: { type: 'grow', small: true } }),
        //   //   ' Flashy ',
        //   //   h('strong', 'toast'),
        //   //   ` message #${this.count} `,
        //   //   h('b-spinner', { props: { type: 'grow', small: true } }),
        //   // ],
        //   'img',
        //   { src: ["images/badgeImg/1001.png"] },
        //   [

        //   ]
        // )
        // Create the title
        const vNodesTitle = h(
          'div',
          { class: ['d-flex', 'flex-grow-1', 'align-items-baseline', 'mr-2'] },
          [
            h('strong', { class: 'mr-2' }, 'The Title'),
            h('small', { class: 'ml-auto text-italics' }, '5 minutes ago')
          ]
        )
        // Pass the VNodes as an array for message and title
        this.$bvToast.toast([vNodesMsg], {
          title: [vNodesTitle],
          solid: true,
          variant: 'info',
          toaster : 'b-toaster-top-center'
        })
      }
    }
 }
</script>

<style>
#background {
  width: 100vw;
  height: 100vh;
}
/* .badge_content{
  //display: inline-block;
  width: 100%;
} */

/* .test{
  width : 1000px;
  height: auto;
} */

.b-toaster.b-toaster-top-center,
.b-toaster.b-toaster-top-full{
  top: 100px;
  right: 33vw;
  left: 20vw;
}
/* $body-bg: #000;
$body-color: #111; */
/* .b-toast{
  max-width: 1000px;
  width: 1000px;
} */
/* #my-toast{
  max-width: 1200px;
  width: 90%;
} */
.toast_body{
  max-width: 1000px;
  width: 40vw;
  height: 20vh;
  background-color: gray;
}
.toast_header{
  max-width: 1000px;
  width: 700px;
}
</style>