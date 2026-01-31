# Validation Groups

`UrlRequest` uses validation groups so one DTO can serve both endpoints. Each group validates different fields.

## Groups

| Group | Endpoint | Validated Fields |
|-------|----------|------------------|
| `ShortenUrl` | `POST /api/v1/shorten-url` | `original_url` — @NotBlank, @Pattern (http:// or https://) |
| `GetOriginalUrl` | `POST /api/v1/get-original-url` | `short_url` — @NotBlank |

## Usage

In the controller, use `@Validated(Group.class)` instead of `@Valid`:

```java
@PostMapping("/shorten-url")
public ResponseEntity<ApiResponse<UrlResponse>> shortenUrl(
    @Validated(UrlRequest.ShortenUrl.class) @RequestBody UrlRequest request) {
    // ...
}

@PostMapping("/get-original-url")
public ResponseEntity<ApiResponse<UrlResponse>> getOriginalUrlFromShortUrl(
    @Validated(UrlRequest.GetOriginalUrl.class) @RequestBody UrlRequest request) {
    // ...
}
```

Only the constraints for the specified group run on each request.

## How It Works

1. **Groups on constraints:** Each validation annotation (`@NotBlank`, `@Pattern`) includes `groups = ShortenUrl.class` or `groups = GetOriginalUrl.class`. By default, constraints belong to the `Default` group; specifying a group restricts when that constraint runs.

2. **Controller triggers validation:** `@Validated(UrlRequest.ShortenUrl.class)` tells Spring to validate the `UrlRequest` using only the `ShortenUrl` group. Similarly, `@Validated(UrlRequest.GetOriginalUrl.class)` runs only `GetOriginalUrl` constraints.

3. **Selective validation:** For `/shorten-url`, only `ShortenUrl` constraints run — so `original_url` is required, and `short_url` is not validated. For `/get-original-url`, only `GetOriginalUrl` constraints run — so `short_url` is required, and `original_url` is ignored. Each endpoint validates only the fields it needs.

4. **Why @Validated instead of @Valid:** `@Valid` does not support groups. Spring's `@Validated` supports the `groups` attribute so the controller can choose which group to validate per endpoint.
