<script lang="ts">
  import type { Bounty } from "$lib/types";
  import BountyShort from "$lib/BountyShort.svelte";
  import { onMount } from "svelte";
  const boxes = 10;
  let bounties: Bounty[] = [];
  function refresh_bounties() {
    let response = fetch("localhost:8080/test")
      .then((res) => res.json)
      .then((json) => json);
    console.log(response);
  }
  onMount(refresh_bounties);
</script>

<div class="page">
  <div class="profile">
    <div class="pheader">
      <img src="https://commons.wikimedia.org/wiki/File:Stickman.png" />
      <h2 style="color:rgba(155, 74, 40, 0.877)">Hunter Guy</h2>
    </div>
    <body>
      <ul id="identifiers" style="color:rgba(155, 74, 40, 0.877)">
        <li><strong>Class:</strong> Cowboy</li>
        <li><strong>Skill Level:</strong> 5</li>
        <li><strong>Location:""</strong>[5,5]</li>
      </ul>

      <dl>
        <dt>Speed</dt>
        <dd>- 5</dd>
        <dt>Draw-Speed</dt>
        <dd>- 5</dd>
        <dt>Discernment</dt>
        <dd>- 5</dd>
      </dl>
    </body>
  </div>
  <div class="feed">
    {#each bounties as bounty}
      <BountyShort {bounty} />
    {/each}
  </div>
</div>

<style>
  dd {
    margin-left: 10px;
  }
  .page {
    background-color: rgba(215, 184, 98, 0.877);
    display: grid;
    grid-template-columns: 30% 70%;
    gap: 10px;
  }
  h2 {
    font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  }
  .profile {
    background-color: rgba(190, 186, 177, 0.877);
    padding: 6px;
    display: inline-block;
    min-height: 100%;
  }
  .pheader {
    text-align: center;
    margin-bottom: 5px;

    padding: 6px;
    background-color: rgba(190, 186, 177, 0.877);
    /* rgba(190, 186, 177, 0.877) */
  }
  #identifiers {
    text-align: left;
  }
  .feed {
    background-color: rgba(190, 186, 177, 0.877);
    display: inline-block;
    padding: 6px;
  }
  .box {
    margin: left;
    text-align: center;
    display: inline-block;
    margin-bottom: 5px;
    width: 33%;
    padding: 4.5px;
    background-color: rgba(190, 186, 177, 0.877);
    /* rgba(190, 186, 177, 0.877) */
  }
  .page {
    min-height: 100%;
  }
  .page,
  body {
    padding: 0;
    margin: 0;
  }
  body,
  :global(html) {
    height: 100%;
  }
</style>
