<script lang="ts">
  import { Loc2Str } from "$lib/types";
  import type { Bounty } from "$lib/types";
  export let bounty: Bounty;
</script>

<article>
  <img src="Stickman.png" alt="stickman criminal" />
  <h4><strong>$$ {bounty.reward} $$</strong></h4>
  <ul id="identifiers" style="color:rgba(155, 74, 40, 0.877)">
    <li><strong>Condition</strong>{bounty.bounty_condition}</li>
    <li><strong>Danger Level:</strong>{bounty.danger}</li>
    <li><strong>Location:</strong>{Loc2Str(bounty.last_known)}</li>
    <li><strong>Styles:</strong>{bounty.styles}</li>
  </ul>

  <p>
    {bounty.name} is a dangerous criminal wanted for being a {bounty.crime}.
    {bounty.client} is offering {bounty.reward} HARD CASH for {bounty.name}, {bounty.bounty_condition}!
    They prefer to see
    {bounty.name} taken out using any of these methods: {bounty.styles}.
    <br />
    Be warned! {bounty.name} is known to have these skills!
  </p>
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
  article {
    border: 2px solid black;
    padding: 6px;
    padding-right: 10px;
  }
</style>
