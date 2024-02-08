extern crate cbindgen;

use cbindgen::Config;
use std::env;

fn main() {
    let crate_dir = env::var("CARGO_MANIFEST_DIR").unwrap();
    let target_dir = format!("{crate_dir}/target");
    println!("cargo:warning=target dir: {}", target_dir);

    let config = Config::from_file("cbindgen.toml").unwrap();
    let output_file = format!("{target_dir}/includes/rs-example-lib.h");

    cbindgen::generate_with_config(&crate_dir, config)
        .unwrap()
        .write_to_file(&output_file);
}
