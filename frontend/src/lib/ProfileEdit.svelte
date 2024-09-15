<script lang="ts">
  import type { Hunter } from "$lib/types";
  import { createEventDispatcher } from "svelte";
  const dispatch = createEventDispatcher();

  export let hunter: Hunter;

  function use() {
    dispatch("use", { hunter: hunter });
  }
</script>

<div class="outer">
  <div class="form">
    <h1>Edit Profile</h1>
    <label for="name">Name</label>
    <input type="text" id="name" bind:value={hunter.name} />
    <label for="skill">How skilled are you?</label>
    <div>
      <input
        type="range"
        id="skill"
        min="0"
        max="9"
        bind:value={hunter.skill}
      />
      <output>{hunter.skill}</output>
    </div>
    <label for="location">Location</label>
    <fieldset>
      <label for="lat">latitude</label>
      <input
        type="number"
        id="lat"
        class="location"
        bind:value={hunter.location.lat}
      />
      <label for="lon">longitude</label>
      <input
        type="number"
        id="lon"
        class="location"
        bind:value={hunter.location.lon}
      />
    </fieldset>
    <label for="speed">How Fast's Your Horse?</label>
    <input type="number" name="speed" id="speed" bind:value={hunter.speed} />
    <label for="draw_speed">How fast of a shooter are you?</label>
    <input
      type="number"
      name="draw_speed"
      id="draw_speed"
      bind:value={hunter.draw_speed}
    />
    <label for="discernment">How good's your eye?</label>
    <input
      type="number"
      name="discernment"
      id="discernment"
      bind:value={hunter.discernment}
    />
    <label for="class">What kind of bounty hunter are you?</label>
    <select name="class" id="class" bind:value={hunter.type}>
      <option value="Gunslinger">Gunslinger</option>
      <option value="Cowboy">Cowboy</option>
      <option value="Sniper">Sniper</option>
      <option value="Trickster">Trickster</option>
      <option value="Sherrif">Sheriff</option>
    </select>
    <input type="submit" name="submit" id="submit" value="use" on:click={use} />
  </div>
</div>

<style>
  .outer {
    opacity: 1;
    position: absolute;
    z-index: 100;
    background-color: #D2B582;
    left: 45%;
    top: 44%;
    margin-left: -40%;
    margin-top: -20%;
    padding: 10px;
    border: 2px solid black;
    border-radius: 10px;
  }
  .form > * {
    display: block;
    padding: 2px;
    border: 0px;
  }
  .form > label {
    margin-top: 10px;
    font-weight: bold;
  }
  .location {
    width: 50px;
  }
  #submit {
    margin-top: 6px;
    font-weight: bold;
    background: #53190e;
    color: white;
    padding: 5px 15px 5px 15px;
    border: 0px;
    border-radius: 5px;
  }

  #skill {
    accent-color: #53190e;
  }

  output {
    margin: 0;
  }

  h1 {
    margin: 0;
    font-size: 20pt;
  }
</style>
