<script lang="ts">
  import type { Bounty, Hunter } from "$lib/types";
  import { getDefaultHunter } from "$lib/types";
  import BountyShort from "$lib/BountyShort.svelte";
  import Profile from "$lib/Profile.svelte";
  import ProfileEdit from "$lib/ProfileEdit.svelte";
  import { onMount } from "svelte";
  const boxes = 10;
  let hunter = getDefaultHunter(); //TODO: hunter stuff
  let bounties: Bounty[] = [];
  let profile_settings: boolean = false;
  let sortOption: string = "relevance"; // Default sorting option
  async function refresh_bounties() {
    let response = await fetch(
      "/api?" +
        new URLSearchParams({
          hunter: JSON.stringify(hunter),
          max: "10",
        }).toString(),
    ).then((res) => res.text());
    bounties = JSON.parse(response);
    console.log(bounties);
  }
  // Apply sorting after fetching the bounties
  sortBounties();

  // Function to sort bounties based on the selected option
  function sortBounties() {
    if (sortOption === "reward") {
      // Sort by reward in descending order
      bounties = [...bounties].sort((a, b) => b.reward - a.reward);
    } else if (sortOption === "name") {
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

  function profile_edit() {
    profile_settings = true;
  }

  // @ts-ignore
  function finish_edit(h) {
    console.log(h);
    hunter = h.detail.hunter;
    profile_settings = false;
    refresh_bounties();
  }
</script>

{#if profile_settings}
  <ProfileEdit {hunter} on:use={finish_edit} />
{/if}

<div class="outer_page">
  <div class="page">
    <div class="profile">
      <Profile {hunter} on:click={profile_edit} />
    </div>
    <div class="feed">
      <label for="sort">Sort By: </label>
      <select
        id="sort"
        bind:value={sortOption}
        on:change={(e) => {
          // @ts-ignore
          changeSort(e.target.value);
        }}
      >
        <option value="relevance">Relevance</option>
        <option value="reward">Reward</option>
        <option value="name">Name</option>
      </select>
      <button on:click={refresh_bounties}>Refresh</button>
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
    gap: 15px;
  }

  .profile,
  .feed {
    background-color: #D2B582;
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
    height: 100%;
    padding: 0;
  }
  .outer_page {
    background-image: linear-gradient(to bottom right, #eb7e55, #53190e);
    height: 100vh;
    padding: 20px;
    box-sizing: border-box;
  }
  :global(body) {
    margin: 0;
  }
  .feed {
    overflow: scroll;
  }

</style>
