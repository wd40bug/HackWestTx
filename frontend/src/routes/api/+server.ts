import type { RequestEvent } from "./$types";

export async function GET({ url, request }: RequestEvent) {
  const max = url.searchParams.get('max') ?? "";
  const hunter = JSON.parse(url.searchParams.get('hunter') ?? "");
  hunter.location = JSON.parse("{\"lat\": -39.492804643893024,\"lon\": 50.43708590314395}");

  // @ts-ignore
  let data = await fetch("http://localhost:8080/dummy_data", {
    method: "POST", headers: {
      "Content-Type": "application/json",
    }, body: JSON.stringify(hunter)
  }
  ).then(res => res.text());

  // @ts-ignore
  return new Response(data);
}
