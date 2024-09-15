<script lang="ts">
  import type {Hunter} from "$lib/types";
  export let hunter: Hunter;
</script>

<style>
  .profile-card {
    width: 800px; /* Adjust width as needed */
    border: 2px solid #000;
    padding: 20px;
    font-family: Arial, sans-serif;
  }
  
  .header {
    margin-bottom: 20px;
    font-size: 24px;
    text-align: center;
  }
  
  .picture {
    width: 100%;
    height: 180px; /* Adjust based on requirement */
    background-color: #ccc;
    margin-bottom: 20px;
  }
  
  .info {
    font-size: 16px;
    margin-bottom: 10px;
  }
  
  .back-button {
    display: block;
    width: 100%;
    padding: 10px;
    background: #007BFF;
    color: white;
    text-align: center;
    text-decoration: none;
    border-radius: 5px;
    margin-top: 20px;
  }
</style>

<div class="profile-card">
  <div class="header">{hunter.name}'s Profile</div>
  <div class="picture"></div>
  <div class="info"><strong>Skill:</strong> {hunter.skill}</div>
  <div class="info"><strong>Location:</strong> {hunter.location}</div>
  <div class="info"><strong>Type:</strong> {hunter.type}</div>
  <div class="info"><strong>Speed:</strong> {hunter.speed}</div>
  <div class="info"><strong>Draw Speed:</strong> {hunter.draw_speed}</div>
  <div class="info"><strong>Discernment:</strong> {hunter.discernment}</div>
  <a href="/" class="back-button">Back</a>
</div>
