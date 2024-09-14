<script>
    import { onMount } from "svelte";
    import { apiData, drinkNames } from './store.js';

    let result = null;

    // onMount(async () => {
    //   fetch("localhost:8080/test")
    //   .then(response => response.json())
    //   .then(data => {
    //             console.log(data);
    //     apiData.set(data);
    //   }).catch(error => {
    //     console.log(error);
    //     return [];
    //   });
    // });

    // $: getString = response;

   
    $: postString = result;
</script>
<div>
    <body>
        <!-- <p>this is our REST test!</p>
        <p>GET: {getString}</p> -->
        <br/>
        <p>POST:</p>
        <form method="POST">
            <label>
                test post
            </label>
            <input 
                name="description"
                autocomplete="off"
            />
        </form>
        <br/>
        <p>PUT:</p>
        <br/>
        <p>DELETE:</p>
        <br/>
    </body>
</div>