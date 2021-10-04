
<template>
  <div id="skeleton-container">
    <div id="head"></div>
    <div id="spine"></div>
    <div id="shoulder-line"></div>
    <div id="left-waist"></div>
    <div id="right-waist"></div>
    <div id="heap"></div>
    <div id="left-arm-up"></div>
    <div id="left-arm-down"></div>
    <div id="right-arm-up"></div>
    <div id="right-arm-down"></div>
    <div id="left-leg-up"></div>
    <div id="left-leg-down"></div>
    <div id="right-leg-up"></div>
    <div id="right-leg-down"></div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#skeleton-container {
  position: relative;
  width: 270px;
  height: 300px;
  border: 2px black solid;
}

#head {
  position: absolute;
  border-radius: 70%;
  border: 3px black solid;
  left: 125px;
  top: 60px;
  width: 20px;
  height: 30px;
}

#spine {

}

#shoulder-line {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 100px;
  width: 50px;
  height: 5px;
}

#left-waist {
  position: absolute;
  border: 3px black solid;
  left: 160px;
  top: 100px;
  width: 75px;
  height: 5px;
  transform-origin: left;
  transform: rotate(90deg);
}

#right-waist {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 100px;
  width: 75px;
  height: 5px;
  transform-origin: left;
  transform: rotate(90deg);
}

#heap {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 175px;
  width: 50px;
  height: 5px;
}

#left-arm-up {
  position: absolute;
  border: 3px black solid;
  left: 160px;
  top: 100px;
  width: 45px;
  height: 5px;
  transform-origin: left;
  transform: rotate(270deg);
}

#left-arm-down {
  position: absolute;
  border: 3px black solid;
  left: 160px;
  top: 55px;
  width: 45px;
  height: 5px;
  transform-origin: left;
  transform: rotate(270deg);
}

#right-arm-up {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 100px;
  width: 45px;
  height: 5px;
  transform-origin: left;
  transform: rotate(270deg);
}

#right-arm-down {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 55px;
  width: 45px;
  height: 5px;
  transform-origin: left;
  transform: rotate(270deg);
}

#left-leg-up {
  position: absolute;
  border: 3px black solid;
  left: 160px;
  top: 175px;
  width: 55px;
  height: 5px;
  transform-origin: left;
  transform: rotate(90deg);
}

#left-leg-down {
  position: absolute;
  border: 3px black solid;
  left: 160px;
  top: 230px;
  width: 50px;
  height: 5px;
  transform-origin: left;
  transform: rotate(90deg);
}

#right-leg-up {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 175px;
  width: 55px;
  height: 5px;
  transform-origin: left;
  transform: rotate(90deg);
}

#right-leg-down {
  position: absolute;
  border: 3px black solid;
  left: 110px;
  top: 230px;
  width: 50px;
  height: 5px;
  transform-origin: left;
  transform: rotate(90deg);
}

#foo {
  position: absolute;
  border-radius: 70%;
  border: 3px black solid;
  left: 125px;
  top: 70px;
  width: 20px;
  height: 30px;
}


</style>