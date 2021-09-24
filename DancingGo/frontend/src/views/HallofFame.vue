<template>
  <div>
    <v-img id="background" src="images/HallofFame/stage3.jpg"></v-img>
    <v-img id="Firecracker" src="images/HallofFame/Firecracker.png"></v-img>
    <div class="board"> 

    </div>
    <v-img id="rank" src="images/HallofFame/rank.png"></v-img>
    <div class="first"></div>
    <div class="second"></div>
    <div class="third"></div>
    <div class="list"> 
      <!-- <vue-scrolling-table>
        <template slot="thead">
          <tr>
          <th v-for="col in columns"
          :class="col.cssClasses"
          :key="col.id">{{ col.title }}</th>
          </tr>
        </template>
        <template slot="tbody">
          <tr v-for="item in items" :key="item.id">
          <td v-for="col in columns"
          :class="col.cssClasses"
          :key="col.id">{{ item[col.id] }}</td>
          </tr>
        </template>
      </vue-scrolling-table> -->
    </div>
    <div class="me"> </div>
  </div>
</template>

<script>

export default {
  name: 'HallofFame',
  data() {
    return {
      
    }
  },
  method: {
   
  },
  mounted() {
     
  }
}
</script>

<style>

html {
  overflow: hidden;
  scrollbar-width: none;
  -ms-overflow-style: none;
}

html::-webkit-scrollbar {
  display: none;
}

#background {
  position: absolute;
  width: 100vw;
  height: 100vh;
}

#Firecracker {
  position: absolute;
  width: 100vw;
  height: 100vh;
}

.board {
 position: absolute;
 background-color: black;
 width: 75vw;
 height: 95vh;
 /* 투명도 */
 opacity: 0.6;
 /* 가운데 정렬 */
 top: 50%;
 left: 50%;
 transform: translate(-50%, -50%);
}

#rank {
  position: absolute;
  width: 40%;
  left: 30.6vw;
  bottom: 47vh;
}

.first {
  position: absolute;
  width: 6vw;
  height: 6vw;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -360%);
  background-color: violet;
}

.second {
  position: absolute;
  width: 6vw;
  height: 6vw;
  top: 50%;
  left: 50%;
  transform: translate(90%, -280%);
  background-color: violet;
}

.third {
  position: absolute;
  width: 6vw;
  height: 6vw;
  top: 50%;
  left: 50%;
  transform: translate(-190%, -300%);
  background-color: violet;
}

.list {
  position: absolute;
  width: 30vw;
  height: 40vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -14%);
  background-color: tomato;
}

.me {
  position: absolute;
  width: 30vw;
  height: 9vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, 405%);
  background-color: lime;
}

</style>