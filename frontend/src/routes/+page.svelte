<script lang="ts">
  import type { Bounty } from "$lib/types";
  import { getDefaultHunter } from "$lib/types";
  import BountyShort from "$lib/BountyShort.svelte";
  import Profile from "$lib/Profile.svelte";
  import { onMount } from "svelte";
  const boxes = 10;
  const hunter = getDefaultHunter(); //TODO: hunter stuff
  let bounties: Bounty[] = [];
  let sortOption: string = 'relevance'; // Default sorting option

  async function refresh_bounties() {
    let response = await fetch(
      "/api?" +
        new URLSearchParams({
          hunter: JSON.stringify(hunter),
          max: "10",
        }).toString(),
    ).then((res) => res.text());
    bounties = JSON.parse(response);

     // Apply sorting after fetching the bounties
     sortBounties();

  }
  // Function to sort bounties based on the selected option
  function sortBounties() {
    if (sortOption === 'reward') {
      // Sort by reward in descending order
      bounties = [...bounties].sort((a, b) => b.reward - a.reward);
    } else if (sortOption === 'name') {
      // Sort by name alphabetically
      bounties = [...bounties].sort((a, b) => a.name.localeCompare(b.name));
    } else {
      // Default sort by relevance (assumed to be the original order from API)
      // If relevance needs specific sorting logic, add it here
    }
  }
  // Update sorting when the option changes
  function changeSort(option: string) {
    sortOption = option;
    sortBounties(); // Re-sort based on the new option
  }


  onMount(refresh_bounties);
</script>

<div class="page">
  <div class="profile">
    <Profile {hunter} />
  </div>
  <div class="feed">
    <!-- Sorting Dropdown -->
    <label for="sort">Sort By: </label>
    <select id="sort" bind:value={sortOption} on:change="{(e) => changeSort(e.target.value)}">
      <option value="relevance">Relevance</option>
      <option value="reward">Reward</option>
      <option value="name">Name</option>
    </select>

    <!-- display bounties -->
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
    padding: 0px 6px;
    display: inline-block;
    height: auto;
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
    padding: 0;
  }
  .page,
  :global(body) {
    padding: 0;
    margin: 0;
  }
  :global(body),
  :global(html) {
    height: 100%;
  }
  .feed {
    overflow: scroll;
    height: 100vh;
  }
</style>
