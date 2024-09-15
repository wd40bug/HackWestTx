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
    console.log(bounties)
  }
     // Apply sorting after fetching the bounties
     sortBounties();

  
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
  </div>
</div>

    <!-- display bounties -->
    {#each bounties as bounty}
      <BountyShort {bounty} />
    {/each}
<div class="outer_page">
  <div class="page">
    <div class="profile">
      <Profile {hunter} />
    </div>
    <div class="feed">
      {#each bounties as bounty}
        <BountyShort {bounty} />
      {/each}
    </div>
  </div>
</div>

<style>
  .page {
    display: grid;
    grid-template-columns: 30% 70%;
    gap: 10px;
  }

  .profile,
  .feed {
    background-color: rgba(256, 256, 256, 0.6);
    border-radius: 8px;
    padding: 17px;
    box-sizing: border-box;
  }
  .profile {
    display: inline-block;
    height: auto;
  }

  .feed {
    display: inline-block;
  }

  .page {
    min-height: 100%;
    padding: 0;
  }
  .outer_page {
    background-image: linear-gradient(to bottom right, #eb7e55, #53190e);
    height: 100vh;
    padding: 10px;
  }
  :global(body) {
    margin: 0;
  }
  .feed {
    overflow: scroll;
    height: 100vh;
  }
</style>
