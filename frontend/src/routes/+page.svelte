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
  onMount(refresh_bounties);

  function profile_edit() {
    profile_settings = true;
  }

  // @ts-ignore
  function finish_edit(h) {
    console.log(h)
    hunter = h.detail.hunter;
    profile_settings = false;
  }
</script>

{#if profile_settings}
  <ProfileEdit {hunter} on:use={finish_edit}/>
{/if}
<div class="outer_page">
  <div class="page">
    <div class="profile">
      <Profile {hunter} on:click={profile_edit} />
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
