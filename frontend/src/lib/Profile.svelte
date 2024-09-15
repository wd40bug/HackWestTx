<script lang="ts">
  import type { Hunter } from "$lib/types";
  import { Loc2Str } from "$lib/types";
  export let hunter: Hunter;
  import img from "$lib/assets/Stickman.png";
  //imgs
  import person1 from "$lib/assets/result(3).png";
  import person2 from "$lib/assets/result(2).png";
  import person3 from "$lib/assets/result(1).png";
  import hat1 from "$lib/assets/hat3(1).png";
  import hat2 from "$lib/assets/hat2(1).png";
  import hat3 from "$lib/assets/hat1(1).png";

  let arr: string[] = [person1, person2, person3];
  const ind: number = Math.floor(Math.random()* arr.length);
  const result: string = arr[ind];

  let arr2: string[] = [hat1, hat2, hat3];
  const ind2: number = Math.floor(Math.random()* arr2.length);
  const result2: string = arr2[ind2];
</script>

<div class="profile-card">
  <div class="header">{hunter.name}'s Profile</div>
  
  <div class="outer_wrapper"><div class="wrapper">
    <div class="picture">
      <img src={result} alt="" />
    </div>
    <div class="picture">
      <img src={result2} alt="" />
    </div>
  </div>
  </div>
  <div class="info"><strong>Skill:</strong> {hunter.skill}</div>
  <div class="info"><strong>Location:</strong> {Loc2Str(hunter.location)}</div>
  <div class="info"><strong>Type:</strong> {hunter.type}</div>
  <div class="info"><strong>Speed:</strong> {hunter.speed}</div>
  <div class="info"><strong>Draw Speed:</strong> {hunter.draw_speed}</div>
  <div class="info"><strong>Discernment:</strong> {hunter.discernment}</div>
  <button class="change-button" on:click>Change</button>
</div>

<style>
  .profile-card {
    width: 100%; /* Adjust width as needed */
    border: 2px solid #000;
    padding: 0;
    font-family: Arial, sans-serif;
  }

  .header {
    margin-bottom: 20px;
    font-size: 24px;
    text-align: center;
  }

  .picture {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%; /* Adjust based on requirement */
    margin-bottom: 20px;
  }

  .wrapper{
    position:relative;
    height: 100%;
  }

  .info {
    font-size: 16px;
    margin-bottom: 10px;
    margin-left: 6px;
  }

  .change-button {
    display: block;
    width: 100%;
    padding: 10px 0px 10px 0px;
    background: #007bff;
    color: white;
    text-align: center;
    text-decoration: none;
    border-radius: 5px;
    margin-top: 20px;
  }

  .outer_wrapper{
    width: 100%;
    aspect-ratio: 6 / 2;
  }

  img {
    width: 100%;
  }
</style>
