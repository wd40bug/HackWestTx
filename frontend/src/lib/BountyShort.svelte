<script lang="ts">
  import { Loc2Str } from "$lib/types";
  import type { Bounty } from "$lib/types";
  export let bounty: Bounty;
  import bounty1 from "$lib/assets/bounty1.png";
  import bounty2 from "$lib/assets/bounty2.png";
  import bounty3 from "$lib/assets/bounty3.png";
  import bounty4 from "$lib/assets/bounty4.png";
  import bounty5 from "$lib/assets/bounty5.png";
  import bounty6 from "$lib/assets/bounty6.png";

  let pic = bounty.pic;
  function getBountyImage(pic: string) {
    switch (pic) {
      case "bounty1":
        return bounty1;
      case "bounty2":
        return bounty2;
      case "bounty3":
        return bounty3;
      case "bounty4":
        return bounty4;
      case "bounty5":
        return bounty5;
      case "bounty6":
        return bounty6;
      default:
        return "https://picsum.photos/512/512.jpg"; // Default image if none match
    }
  }
</script>

<article>
  <img class="picture" src={getBountyImage(bounty.pic)} alt="criminal" />
  <h4><strong>$$ {bounty.reward},000 $$</strong></h4>
  <ul id="identifiers" style="color:rgba(155, 74, 40, 0.877)">
    <li><strong>Condition:</strong>{bounty.bounty_condition}</li>
    <li><strong>Danger Level:</strong>{bounty.danger}</li>
    <li><strong>Last Seen:</strong>{Loc2Str(bounty.last_known)}</li>
    <li><strong>Styles:</strong>{bounty.styles}</li>
  </ul>

  <p>
    <strong>{bounty.name}</strong> is a dangerous criminal wanted for being a {bounty.crime}.
    {bounty.client} is offering ${bounty.reward},000 HARD CASH for
    <strong>{bounty.name}</strong>, {bounty.bounty_condition}!
    <strong>{bounty.name}</strong>
    is known to have the following styles:
  </p>
  <ul>
    {#each bounty.styles as style}
      <li>{style}</li>
    {/each}
  </ul>
  <br />
  <p><strong>stats</strong></p>
  <dl>
    <dt>Speed</dt>
    <dd>- {bounty.speed}</dd>
    <dt>Draw-Speed</dt>
    <dd>- {bounty.draw_speed}</dd>
    <dt>Slipperiness</dt>
    <dd>- {bounty.slipperiness}</dd>
  </dl>
</article>

<style>
  .picture {
    width: 25%;
    height: 25%;
  }
  article {
    border: 4px solid black;
    padding: 6px;
    padding-right: 10px;
    margin: 10px;
    border-radius: 5px;
  }
  h4 {
    color: green;
    font-size: 24pt;
    margin: 0;
  }
</style>
