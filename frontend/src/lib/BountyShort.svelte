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

  let arr: string[] = [bounty1, bounty2, bounty3, bounty4, bounty5, bounty6];
  const ind: number = Math.floor(Math.random() * arr.length);
  const result: string = arr[ind];
</script>

<article>
  <img class="picture" src={result} alt="criminal" />
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
  .picture {
    width: 25%;
    height: 25%;
  }
  article {
    border: 2px solid black;
    padding: 6px;
    padding-right: 10px;
  }
</style>
